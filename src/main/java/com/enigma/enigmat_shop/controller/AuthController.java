package com.enigma.enigmat_shop.controller;

import com.enigma.enigmat_shop.entity.Role;
import com.enigma.enigmat_shop.entity.User;
import com.enigma.enigmat_shop.entity.UserDetailImpl;
import com.enigma.enigmat_shop.request.LoginRequest;
import com.enigma.enigmat_shop.request.RegisterRequest;
import com.enigma.enigmat_shop.response.LoginResponse;
import com.enigma.enigmat_shop.response.UserResponse;
import com.enigma.enigmat_shop.security.jwt.JwtUtils;
import com.enigma.enigmat_shop.service.RoleService;
import com.enigma.enigmat_shop.service.UserService;
import com.enigma.enigmat_shop.util.WebResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/register")
    public ResponseEntity<WebResponse<?>> register(@RequestBody RegisterRequest request) {

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        Set<Role> roleSet = new HashSet<>();
        Set<String> roles = request.getRoles();
        for (String role : roles) {
            Role role1 = roleService.create(role);
            roleSet.add(role1);
        }

        UserResponse userResponse = userService.create(user, roleSet);
        WebResponse<?> response = new WebResponse<>(
                "Successfully created new user",
                userResponse
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<WebResponse<?>> login(@RequestBody LoginRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailImpl userDetail = (UserDetailImpl) authentication.getPrincipal();
        Set<String> roles = new HashSet<>();
        for (GrantedAuthority authority : userDetail.getAuthorities()) {
            roles.add(authority.getAuthority());
        }
        LoginResponse loginResponse = new LoginResponse(jwt, roles);

        WebResponse<?> response = new WebResponse<>("Successfully Login", loginResponse);

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }
}
