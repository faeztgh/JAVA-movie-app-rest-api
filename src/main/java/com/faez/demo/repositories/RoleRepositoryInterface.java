package com.faez.demo.repositories;

import com.faez.demo.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author unknown
 */
@Repository
public interface RoleRepositoryInterface extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
