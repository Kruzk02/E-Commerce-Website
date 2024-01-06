package com.vn.ECommerce.Service;

import com.vn.ECommerce.DTO.CartDTO;
import com.vn.ECommerce.Model.Cart;
import com.vn.ECommerce.Model.Product;

import java.util.List;

public interface CartService {

    List<Cart> getCarts();
    Cart addCart(CartDTO cartDTO);
    void updateCart(Cart cart);
    void deleteCart(Cart cart);
}
