package io.github.issowl.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import io.github.common.exception.BizCodeEnum;
import io.github.common.utils.R;
import io.github.issowl.gateway.utils.JWTUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class JWTAuthFilter implements GlobalFilter {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        String url = request.getURI().getPath();
        String token = request.getHeaders().getFirst("Authorization");

        // 访问认证服务器的加入到白名单
        if (url.contains("/auth/")) {
            return chain.filter(exchange);
        }

        // 从请求头中取得token
        if (StringUtils.isEmpty(token)) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return getVoidMono(response, BizCodeEnum.TOKEN_MISSION);
        }

        // 校验token
        try {
            JWTUtils.verify(token);
        } catch (JWTDecodeException jwtDecodeException) {
            return getVoidMono(response, BizCodeEnum.TOKEN_INVALID);
        } catch (SignatureVerificationException signatureVerificationException) {
            return getVoidMono(response, BizCodeEnum.TOKEN_SIGNATURE_INVALID);
        } catch (TokenExpiredException tokenExpiredException) {
            return getVoidMono(response, BizCodeEnum.TOKEN_EXPIRED);
        } catch (Exception e) {
            return getVoidMono(response, BizCodeEnum.UNKNOWN_ERROR);
        }

        // 检查redis中是否有对应token
        String studentId = JWTUtils.getStudentId(token);
        String targetToken = (String)redisTemplate.opsForHash().get(studentId, "token");
        if (!token.equals(targetToken)) {
            return getVoidMono(response, BizCodeEnum.TOKEN_ERROR);
        }

        return chain.filter(exchange);
    }

     private Mono<Void> getVoidMono(ServerHttpResponse serverHttpResponse, BizCodeEnum bizCodeEnum) {
         serverHttpResponse.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
         R response = R.error(bizCodeEnum.getCode(), bizCodeEnum.getMsg());
         DataBuffer dataBuffer = serverHttpResponse.bufferFactory().wrap(JSON.toJSONString(response).getBytes());
         return serverHttpResponse.writeWith(Flux.just(dataBuffer));
    }
}
