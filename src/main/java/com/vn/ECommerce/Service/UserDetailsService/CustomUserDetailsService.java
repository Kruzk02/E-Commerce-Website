package com.vn.ECommerce.Service.UserDetailsService;

import com.vn.ECommerce.Model.User;
import com.vn.ECommerce.Service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findUserByUsername(username);
        if(user == null){
            return new org.springframework.security.core.userdetails.User(
                    " "," ",
                    true,true,
                    true,true,
                    null
            );
        }
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(),
                true, true,
                true, true,
                null
        );
    }
}
