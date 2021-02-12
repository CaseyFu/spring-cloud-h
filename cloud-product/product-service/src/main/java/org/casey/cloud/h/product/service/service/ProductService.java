package org.casey.cloud.h.product.service.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.casey.cloud.h.product.service.entity.Product;

/**
* @InterfaceName ProductService
* @Author Fu Kai
* @Description ProductService
* @Date 2020-12-26 23:31:40
*/

public interface ProductService extends IService<Product> {
    /**
     * 减少库存
     * @param productId
     * @param amount
     * @return
     */
    int decreaseAmount(String productId, Integer amount);
}
