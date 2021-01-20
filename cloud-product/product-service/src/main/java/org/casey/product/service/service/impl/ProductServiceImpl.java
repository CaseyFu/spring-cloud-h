package org.casey.product.service.service.impl;


// import io.seata.spring.annotation.GlobalTransactional;
import org.casey.product.service.entity.Product;
import org.casey.product.service.mapper.ProductMapper;
import org.casey.product.service.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName ProductServiceImpl
 * @Author Fu Kai
 * @Description ProductServiceImpl
 * @Date 2020-12-26 23:31:40
 */

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
    private final ProductMapper productMapper;

    @Autowired
    public ProductServiceImpl(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    // @GlobalTransactional(rollbackFor = Exception.class)
    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public int decreaseAmount(String productId, Integer amount) {
        Product product = productMapper.selectById(productId);
        if (null == product) {
            throw new RuntimeException("product is null! ");
        }
        int afterSubAmount = product.getAmount() - amount;
        if (afterSubAmount < 0) {
            throw new RuntimeException("count < 0! ");
        }
        product.setAmount(afterSubAmount);
        return productMapper.updateById(product);
    }
}
