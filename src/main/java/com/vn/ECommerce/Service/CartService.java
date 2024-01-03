package com.vn.ECommerce.Service;

import com.vn.ECommerce.Model.Cart;

import java.util.List;

public interface CartService {

    List<Cart> getCarts();
    Cart addCart(Cart cart);
    void updateCart(Cart cart);
    void deleteCart(Cart cart);
}
