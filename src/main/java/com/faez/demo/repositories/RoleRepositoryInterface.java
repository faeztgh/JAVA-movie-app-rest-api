package com.faez.demo.repositories;

import com.faez.demo.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author unknown
 */
public interface RoleRepositoryInterface extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
