package com.vn.ECommerce.Repository;

import com.vn.ECommerce.Model.Privilege;
import com.vn.ECommerce.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege,Long> {
    @Query("SELECT u FROM Privilege u WHERE u.name = ?1")
    Privilege findByName(String name);
}
