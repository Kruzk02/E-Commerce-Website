package com.vn.ECommerce.Service;

import com.vn.ECommerce.Model.User;

public interface UserService {
    User saveUser(User user);
    User findUserByEmail(String email);
    User findUserByUsername(String username);
}
