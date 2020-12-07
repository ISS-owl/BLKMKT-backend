package io.github.common.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.common.entity.PageParam;
import io.github.common.xss.SQLFilter;
import org.apache.commons.lang.StringUtils;

/**
 * 查询参数
 *
 * @author Zhihao Shen
 * @email zhihaoshen7@qq.com
 * @date 2020-12-06 23:13:26
 */
public class Query<T> {

    public IPage<T> getPage(PageParam params) {
        return this.getPage(params, null, false);
    }

    public IPage<T> getPage(PageParam params, String defaultOrderField, boolean isAsc) {
        //分页参数
        long curPage = params.getPage() == null? 1 : params.getPage();
        long limit = params.getLimit() == null? 10 : params.getLimit();

        //分页对象
        Page<T> page = new Page<>(curPage, limit);

        //排序字段
        //防止SQL注入（因为sidx、order是通过拼接SQL实现排序的，会有SQL注入风险）
        String orderField = SQLFilter.sqlInject(params.getSidx());
        String order = params.getOrder();


        //前端字段排序
        if(StringUtils.isNotEmpty(orderField) && StringUtils.isNotEmpty(order)){
            if(Constant.ASC.equalsIgnoreCase(order)) {
                return  page.addOrder(OrderItem.asc(orderField));
            }else {
                return page.addOrder(OrderItem.desc(orderField));
            }
        }

        //没有排序字段，则不排序
        if(StringUtils.isBlank(defaultOrderField)){
            return page;
        }

        //默认排序
        if(isAsc) {
            page.addOrder(OrderItem.asc(defaultOrderField));
        }else {
            page.addOrder(OrderItem.desc(defaultOrderField));
        }

        return page;
    }
}
