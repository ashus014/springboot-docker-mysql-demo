package com.singashi.mysql.docker.demo.controller;
import com.singashi.mysql.docker.demo.entity.Product;
import com.singashi.mysql.docker.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableCaching
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping("/addProduct")
    public Product addProduct(@RequestBody Product product){
        return service.saveProduct(product);
    }

    @PostMapping("/addProducts")
    public List<Product> addProducts(@RequestBody List<Product> products){
        return service.saveProducts(products);
    }

    @GetMapping("/products")
    public List<Product> findAllProducts(){
        return service.getProducts();
    }

    @GetMapping("/productById/{id}")
    @Cacheable(value = "productById", key = "#id")
    public Product findProductById(@PathVariable int id){
        return service.getProductById(id);
    }

    @GetMapping("/product/{name}")
    @Cacheable(value = "product", key = "#name")
    public Product findProductByName(@PathVariable String name){
        return service.getProductByName(name);
    }

    @PutMapping("/update")
    public Product updateProduct(@RequestBody Product product){
        return service.updateProduct(product);
    }

    @DeleteMapping("/delete/{id}")
    @CacheEvict(value = "delete", allEntries = true)
    public String deleteProduct(@PathVariable int id){
        return service.deleteProduct(id);
    }

}
