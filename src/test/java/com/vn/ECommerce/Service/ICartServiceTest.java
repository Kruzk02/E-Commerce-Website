package com.vn.ECommerce.Service;

import com.vn.ECommerce.Model.Cart;
import com.vn.ECommerce.Repository.CartRepository;
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

public class ICartServiceTest {

    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private ICartService cartService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetCarts() {
        List<Cart> carts = new ArrayList<>();
        carts.add(new Cart());
        carts.add(new Cart());

        when(cartRepository.findAll()).thenReturn(carts);

        List<Cart> result = cartService.getCarts();

        assertEquals(carts, result);
        verify(cartRepository, times(1)).findAll();
    }

    @Test
    public void testAddCart() {
        Cart cart = new Cart();

        when(cartRepository.save(any(Cart.class))).thenReturn(cart);

        Cart result = cartService.addCart(new Cart());

        assertEquals(cart, result);
        verify(cartRepository, times(1)).save(any(Cart.class));
    }

    @Test
    public void testUpdateCart() {
        Cart cart = new Cart();
        cartService.updateCart(cart);
        verify(cartRepository, times(1)).save(cart);
    }

    @Test
    public void testDeleteCart() {
        Cart cart = new Cart();
        cartService.deleteCart(cart);
        verify(cartRepository, times(1)).delete(cart);
    }
}
