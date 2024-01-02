package com.vn.ECommerce.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "cart"),
               inverseJoinColumns = @JoinColumn(name = "product")
    )
    private List<Product> products;
}
