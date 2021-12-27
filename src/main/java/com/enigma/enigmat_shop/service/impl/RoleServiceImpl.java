package com.enigma.enigmat_shop.service.impl;

import com.enigma.enigmat_shop.entity.Role;
import com.enigma.enigmat_shop.exception.NotFoundException;
import com.enigma.enigmat_shop.repository.RoleRepository;
import com.enigma.enigmat_shop.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import static com.enigma.enigmat_shop.entity.UserRole.ADMIN_ROLE;
import static com.enigma.enigmat_shop.entity.UserRole.USER_ROLE;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role create(String strRole) {
        if (strRole == null) {
            if (!roleRepository.existsByRole(USER_ROLE)) {
                // buat baru ke database role sebagai USER_ROLE
                Role userRole = new Role(USER_ROLE);
                return roleRepository.save(userRole);
            }
            return roleRepository.findByRole(USER_ROLE)
                    .orElseThrow(() -> new NotFoundException("Error: Role not found"));
        } else {
            // user mengirimkan role admin atau tidak ke server
            if (strRole.equalsIgnoreCase("admin")) {
                // ada atau tidak di database
                if (!roleRepository.existsByRole(ADMIN_ROLE)) {
                    Role adminRole = new Role(ADMIN_ROLE);
                    return roleRepository.save(adminRole);
                }
                return roleRepository.findByRole(ADMIN_ROLE)
                        .orElseThrow(() -> new NotFoundException("Error: Role not found"));
            } else {
                if (!roleRepository.existsByRole(USER_ROLE)) {
                    // buat baru ke database role sebagai USER_ROLE
                    Role userRole = new Role(USER_ROLE);
                    return roleRepository.save(userRole);
                }
                return roleRepository.findByRole(USER_ROLE)
                        .orElseThrow(() -> new NotFoundException("Error: Role not found"));
            }
        }
    }
}
