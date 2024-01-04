package com.vn.ECommerce.Repository;

import com.vn.ECommerce.Model.Role;
import com.vn.ECommerce.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    @Query("SELECT u FROM Role u WHERE u.name = ?1")
    Role findByName(String name);
}
