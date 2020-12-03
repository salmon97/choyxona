package com.example.choyxona.repository;

import com.example.choyxona.entity.Role;
import com.example.choyxona.entity.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByRoleName(RoleName roleName);


}
