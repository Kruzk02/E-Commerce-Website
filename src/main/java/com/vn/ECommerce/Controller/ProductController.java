package com.vn.ECommerce.Controller;

import com.vn.ECommerce.DTO.ProductDTO;
import com.vn.ECommerce.Model.Product;
import com.vn.ECommerce.Service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    private final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getProduct();
        logger.info("Retrieved {} products", products.size());
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        try {
            Product product = productService.getProductById(id);
            logger.info("Retrieved product with id {}", id);
            return ResponseEntity.ok(product);
        } catch (Exception e) {
            logger.warn("Product with id {} not found", id);
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestBody ProductDTO productDTO) {
        Product product = productService.create(productDTO);
        logger.info("Created product with id {}", product.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        try {
            Product product = productService.update(id,productDTO);
            logger.info("Updated product with id {}", id);
            return ResponseEntity.ok(product);
        } catch (Exception e) {
            logger.warn("Product with id {} not found for update", id);
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id) {
        try {
            Product product = productService.delete(id);
            logger.info("Deleted product with id {}", id);
            return ResponseEntity.ok(product);
        } catch (Exception e) {
            logger.warn("Product with id {} not found for delete", id);
            return ResponseEntity.notFound().build();
        }
    }
}

