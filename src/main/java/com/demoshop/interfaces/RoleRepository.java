package com.demoshop.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demoshop.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
