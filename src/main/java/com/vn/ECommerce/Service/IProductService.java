package com.vn.ECommerce.Service;

import com.vn.ECommerce.DTO.ProductDTO;
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
    public Product create(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        return productRepository.save(product);
    }

    @Override
    public Product update(Long id,ProductDTO productDTO) {
        Product existingProduct = productRepository.findById(id).orElse(null);

        if(existingProduct != null){
            existingProduct.setName(productDTO.getName());
            existingProduct.setPrice(productDTO.getPrice());

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
