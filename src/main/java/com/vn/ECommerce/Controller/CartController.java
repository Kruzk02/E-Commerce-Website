package com.vn.ECommerce.Controller;

import com.vn.ECommerce.DTO.CartDTO;
import com.vn.ECommerce.Jwt.JwtService;
import com.vn.ECommerce.Model.Cart;
import com.vn.ECommerce.Model.User;
import com.vn.ECommerce.Service.ICartService;
import com.vn.ECommerce.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    private final ICartService cartService;
    private final JwtService jwtService;
    private final UserService userService;
    @Autowired
    public CartController(ICartService cartService, JwtService jwtService, UserService userService) {
        this.cartService = cartService;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<Cart>> getCarts() {
        List<Cart> carts = cartService.getCarts();
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Cart> addCart(@RequestHeader("Authorization") String authHeader,@RequestBody CartDTO cartDTO) {
        String token = extractToken(authHeader);

        if(token != null){
            String username = jwtService.extractUsername(token);
            User user = userService.findUserByUsername(username);

            cartDTO.setUser(user);
            Cart addedCart = cartService.addCart(cartDTO);
            return new ResponseEntity<>(addedCart, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PutMapping
    public ResponseEntity<Void> updateCart(@RequestHeader("Authorization")String authHeader,@RequestBody CartDTO cartDTO) {
        String token = extractToken(authHeader);

        if(token != null){
            String username = jwtService.extractUsername(token);
            User user = userService.findUserByUsername(username);

            cartDTO.setUser(user);
            cartService.updateCart(cartDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    }

    private String extractToken(String authHeader){
        if(authHeader != null && authHeader.startsWith("Bearer ")){
            return authHeader.substring(7);
        }
        return null;
    }
}
