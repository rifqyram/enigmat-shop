package com.enigma.enigmat_shop.service;

import com.enigma.enigmat_shop.entity.Role;
import com.enigma.enigmat_shop.entity.User;
import com.enigma.enigmat_shop.response.UserResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Set;

public interface UserService extends UserDetailsService {

    UserResponse create(User user, Set<Role> roles);

}
