package com.vn.ECommerce.Controller;

import com.vn.ECommerce.DTO.CartDTO;
import com.vn.ECommerce.Model.Cart;
import com.vn.ECommerce.Model.User;
import com.vn.ECommerce.Service.ICartService;
import com.vn.ECommerce.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    private final ICartService cartService;
    private final IUserService userService;
    @Autowired
    public CartController(ICartService cartService, IUserService userService) {
        this.cartService = cartService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<Cart>> getCarts() {
        List<Cart> carts = cartService.getCarts();
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Cart> addCart(@RequestBody CartDTO cartDTO) {
        Cart addedCart = cartService.addCart(cartDTO);
        return new ResponseEntity<>(addedCart, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> updateCart(@RequestBody Cart cart) {
        cartService.updateCart(cart);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
