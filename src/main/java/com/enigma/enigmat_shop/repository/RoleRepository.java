package com.enigma.enigmat_shop.repository;

import com.enigma.enigmat_shop.entity.Role;
import com.enigma.enigmat_shop.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByRole(UserRole role);

    Boolean existsByRole(UserRole role);
}
