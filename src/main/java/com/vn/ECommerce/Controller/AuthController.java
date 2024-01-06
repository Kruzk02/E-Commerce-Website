package com.vn.ECommerce.Controller;

import com.vn.ECommerce.DTO.LoginDTO;
import com.vn.ECommerce.DTO.SignupDTO;
import com.vn.ECommerce.Model.User;
import com.vn.ECommerce.Service.IUserService;
import com.vn.ECommerce.Service.UserService;
import io.micrometer.common.util.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;

@RestController
@RequestMapping("/api")
public class AuthController {
    private final IUserService userService;
    private final AuthenticationManager authenticationManager;
    private final Logger logger = LoggerFactory.getLogger(AuthController.class);
    @Autowired
    public AuthController(IUserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginDTO loginDTO){
        try{
            logger.info("Login attempt for username: {}", loginDTO.getUsername());
            if (StringUtils.isEmpty(loginDTO.getUsername()) || StringUtils.isEmpty(loginDTO.getPassword())) {

                logger.warn("Invalid login request. Username or password is empty.");
                return ResponseEntity.badRequest().body("Username and password cannot be empty.");
            }

            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDTO.getUsername(), loginDTO.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            logger.info("User '{}' logged in successfully.", loginDTO.getUsername());
            return ResponseEntity.ok("successfully Login.");
        }catch (Exception e){
            logger.warn("Login failed for username: {}",loginDTO.getUsername());
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupDTO signupDTO){
        logger.info("Registration attempt for username: {}",signupDTO.getUsername());

        User existingEmail = userService.findUserByEmail(signupDTO.getEmail());

        if(existingEmail != null){
            logger.warn("Registration failed. Email '{}' is already taken",signupDTO.getEmail());
            return ResponseEntity.badRequest().body("Email is already taken");
        }

        userService.saveUser(signupDTO);
        logger.info("User '{}' registered successfully.", signupDTO.getUsername());

        return ResponseEntity.ok("User registered successfully");
    }
}
