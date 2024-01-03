package com.vn.ECommerce.Repository;

import com.vn.ECommerce.Model.Cart;
import com.vn.ECommerce.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
}
