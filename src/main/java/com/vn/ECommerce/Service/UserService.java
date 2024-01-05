package com.vn.ECommerce.Service;

import com.vn.ECommerce.DTO.SignupDTO;
import com.vn.ECommerce.Model.User;

public interface UserService {
    User saveUser(SignupDTO signupDTO);
    User findUserByEmail(String email);
    User findUserByUsername(String username);
}
