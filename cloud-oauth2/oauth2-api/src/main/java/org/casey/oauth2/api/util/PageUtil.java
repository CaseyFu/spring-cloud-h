package org.casey.oauth2.api.util;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.casey.common.core.Query;


/**
 * @ClassName Page
 * @Author Fu Kai
 * @Description todo
 * @Date 2021/1/9 16:58
 */

public class PageUtil {

    /**
     * 根据分页参数封装分页对象
     *
     * @return Page
     */
    public static <T> Page<T> getPageByQuery(Query query) {
        Page<T> page = new Page<>();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());
        OrderItem orderItem = new OrderItem();
        orderItem.setAsc(query.getAsc());
        orderItem.setColumn(query.getOrderBy());
        page.addOrder(orderItem);
        return page;
    }
}
