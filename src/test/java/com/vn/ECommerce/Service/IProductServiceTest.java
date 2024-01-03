package com.vn.ECommerce.Service;

import com.vn.ECommerce.Model.Product;
import com.vn.ECommerce.Repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class IProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private IProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetProduct() {
        List<Product> products = new ArrayList<>();
        when(productRepository.findAll()).thenReturn(products);

        List<Product> result = productService.getProduct();

        assertEquals(products, result);
    }

    @Test
    void testGetProductById() {
        long productId = 1L;
        Product product = new Product();
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        Product result = productService.getProductById(productId);

        assertEquals(product, result);
    }

    @Test
    void testCreate() {
        Product product = new Product();
        when(productRepository.save(product)).thenReturn(product);

        Product result = productService.create(product);

        assertEquals(product, result);
    }

    @Test
    void testUpdate() {
        long productId = 1L;
        Product existingProduct = new Product();
        Product updatedProduct = new Product();
        when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(existingProduct)).thenReturn(updatedProduct);

        Product result = productService.update(productId, updatedProduct);

        assertEquals(updatedProduct, result);
    }

    @Test
    void testDelete() {
        long productId = 1L;
        Product product = new Product();
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        Product result = productService.delete(productId);

        assertEquals(product, result);
        verify(productRepository, times(1)).delete(product);
    }
}
