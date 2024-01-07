package com.vn.ECommerce.Service;

import com.vn.ECommerce.DTO.CartDTO;
import com.vn.ECommerce.Model.Cart;
import com.vn.ECommerce.Model.Product;
import com.vn.ECommerce.Model.User;
import com.vn.ECommerce.Repository.CartRepository;
import com.vn.ECommerce.Repository.ProductRepository;
import com.vn.ECommerce.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ICartService implements CartService {

    private final CartRepository cartRepository;

    @Autowired
    public ICartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public List<Cart> getCarts() {
        return cartRepository.findAll();
    }

    @Override
    public Cart addCart(CartDTO cartDTO) {
        Cart cart = new Cart();
        cart.setUser(cartDTO.getUser());
        cart.setProducts(cartDTO.getProducts());
        return cartRepository.save(cart);
    }

    @Override
    public void updateCart(CartDTO cartDTO) {
        Cart cart = new Cart();
        cart.setUser(cartDTO.getUser());
        cart.setProducts(cartDTO.getProducts());
        cartRepository.save(cart);
    }

    @Override
    public void deleteCart(Cart cart) {
        cartRepository.delete(cart);
    }
}
