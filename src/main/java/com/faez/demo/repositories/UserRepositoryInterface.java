package com.faez.demo.repositories;

import com.faez.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author unknown
 */
@Repository
public interface UserRepositoryInterface extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
