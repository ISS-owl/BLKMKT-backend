package io.github.blkmkt.user.aop;

import io.github.common.entity.Response;
import io.github.common.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice(basePackages = "io.github.blkmkt.user.controller")
public class UserAdvice {
    @ExceptionHandler(value = Exception.class)
    public Response handleValidException(MethodArgumentNotValidException exception){
        Map<String, String> map = new HashMap<>();
        BindingResult bindingResult = exception.getBindingResult();
        bindingResult.getFieldErrors().forEach(fieldError -> {
            String message = fieldError.getDefaultMessage();
            String field = fieldError.getField();
            map.put(field,message);
        });

        log.error("数据校验出现问题{}, 异常类型{}",exception.getMessage(), exception.getClass());
        return ResponseUtils.error(400, "data validation error", map);
    }
}
