package com.faez.demo.services.interfaces;

import com.faez.demo.models.User;
import javassist.NotFoundException;

import java.util.List;

/**
 * @author unknown
 */
public interface IUserService {
    User saveUser(User user);

    User getUser(String username);

    List<User> getUsers();

    User getUserById(Long id) throws NotFoundException;


}
