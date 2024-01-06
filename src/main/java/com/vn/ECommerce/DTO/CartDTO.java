package com.vn.ECommerce.DTO;

import com.vn.ECommerce.Model.Product;
import com.vn.ECommerce.Model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {

    private User user;
    private List<Product> products;
}
