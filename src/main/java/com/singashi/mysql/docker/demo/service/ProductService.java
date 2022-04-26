package com.singashi.mysql.docker.demo.service;
import com.singashi.mysql.docker.demo.dao.ProductRepository;
import com.singashi.mysql.docker.demo.entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository repository;

    public Product saveProduct(Product product) {
        logger.info("calling saveProduct...");
        return repository.save(product);
    }

    public List<Product> saveProducts(List<Product> products) {
        logger.info("calling saveProducts...");
        return repository.saveAll(products);
    }

    public List<Product> getProducts() {
        logger.info("calling getProducts...");
        return repository.findAll();
    }

    public Product getProductById(int id) {
        logger.info("calling getProductById...");
        return repository.findById(id).orElse(null);
    }

    public Product getProductByName(String name) {
        logger.info("calling getProductByName...");
        return repository.findByName(name).orElse(null);
    }

    public String deleteProduct(int id) {
        logger.info("calling deleteProduct...");
        repository.deleteById(id);
        return "Product-removed";
    }

    public Product updateProduct(Product product) {
        logger.info("calling updateProduct...");
        Product existingProduct = repository.findById(product.getId()).orElse(null);
        existingProduct.setName(product.getName());
        existingProduct.setQuantity(product.getQuantity());
        existingProduct.setPrice(product.getPrice());
        return repository.save(existingProduct);
    }

}
