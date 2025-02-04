package com.learn.mymoviedb.repository;

import com.learn.mymoviedb.models.ERole;
import com.learn.mymoviedb.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
