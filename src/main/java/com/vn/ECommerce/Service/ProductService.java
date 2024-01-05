package com.vn.ECommerce.Service;

import com.vn.ECommerce.DTO.ProductDTO;
import com.vn.ECommerce.Model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProduct();
    Product getProductById(Long id);
    Product create(ProductDTO productDTO);
    Product update(Long id,ProductDTO productDTO);
    Product delete(Long id);

}
