package com.vn.ECommerce.Service;

import com.vn.ECommerce.DTO.SignupDTO;
import com.vn.ECommerce.Model.User;
import com.vn.ECommerce.Repository.RoleRepository;
import com.vn.ECommerce.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class IUserService implements UserService{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    @Autowired
    public IUserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public User saveUser(SignupDTO signupDTO) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(signupDTO.getPassword());
        signupDTO.setPassword(encodedPassword);

        User user = new User();
        user.setEmail(signupDTO.getEmail());
        user.setUsername(signupDTO.getUsername());
        user.setPassword(signupDTO.getPassword());
        user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_NAME")));

        return userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }
}
