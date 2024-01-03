package com.vn.ECommerce.Service;

import com.vn.ECommerce.Model.Product;
import com.vn.ECommerce.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IProductService implements ProductService{

    private final ProductRepository productRepository;

    @Autowired
    public IProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product update(Long id, Product product) {
        Product existingProduct = productRepository.findById(id).orElse(null);

        if(existingProduct != null){
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());

            return productRepository.save(existingProduct);
        }
        return null;
    }

    @Override
    public Product delete(Long id) {
        Product existingProduct = productRepository.findById(id).orElse(null);

        if (existingProduct != null) {
            productRepository.delete(existingProduct);
        }
        return null;
    }
}
