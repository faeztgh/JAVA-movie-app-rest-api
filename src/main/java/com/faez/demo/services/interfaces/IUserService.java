package com.faez.demo.services.interfaces;

import com.faez.demo.models.Role;
import com.faez.demo.models.User;

import java.util.List;

/**
 * @author unknown
 */
public interface IUserService {
    User saveUser(User user);

    Role saveRole(Role role);

    void addRoleToUser(String username, String roleName);

    User getUser(String username);

    List<User> getUsers();
}
