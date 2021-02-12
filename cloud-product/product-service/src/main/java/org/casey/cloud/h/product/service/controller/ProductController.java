package org.casey.cloud.h.product.service.controller;

import org.casey.cloud.h.common.core.Result;
import org.casey.cloud.h.product.service.entity.Product;
import org.casey.cloud.h.product.service.service.ProductService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Api;

import java.util.List;

/**
 * @ClassName ProductController
 * @Author Fu Kai
 * @Description ProductController
 * @Date 2020-12-26 23:31:40
 */

@RestController
@RequestMapping("/product")
@Api(tags = {"Product接口"})
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @ApiOperation(value = "value", notes = "注释")
    @GetMapping("/hello")
    public Result hello() {
        return Result.success(null, "hello");
    }

    @PostMapping
    public boolean insert(@RequestBody Product product) {
        return productService.save(product);
    }

    @DeleteMapping("/{productId}")
    public boolean delete(@PathVariable Long productId) {
        return productService.removeById(productId);
    }

    @PutMapping
    public boolean update(@RequestBody Product product) {
        return productService.updateById(product);
    }

    @GetMapping("/{productId}")
    public Product product(@PathVariable String productId) {
        return productService.getById(productId);
    }

    @GetMapping("/list")
    public List<Product> list() {
        return productService.list();
    }

    @PutMapping("/decrease/amount")
    public Result decreaseAmount(@RequestParam String productId, @RequestParam Integer amount) {
        return Result.success(productService.decreaseAmount(productId, amount), "");
    }

}
