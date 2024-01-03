package com.vn.ECommerce.Repository;

import com.vn.ECommerce.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query("SELECT u FROM User u WHERE u.email = ?1")
    User findUserByEmail(String email);
    @Query("SELECT u FROM User u WHERE u.username = ?1")
    User findUserByUsername(String username);
}
