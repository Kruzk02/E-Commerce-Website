package com.vn.ECommerce.Service;

import com.vn.ECommerce.Model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProduct();
    Product getProductById(Long id);
    Product create(Product product);
    Product update(Long id,Product product);
    Product delete(Long id);

}
