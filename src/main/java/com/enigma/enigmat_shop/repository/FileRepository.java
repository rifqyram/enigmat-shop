package com.enigma.enigmat_shop.repository;

import com.enigma.enigmat_shop.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File, String> {
}
