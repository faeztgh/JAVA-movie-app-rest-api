package com.faez.demo.services.interfaces;

import com.faez.demo.models.User;
import javassist.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author unknown
 */
public interface IUserService {
    User saveUser(User user);

    User getUser(String username);

    List<User> getUsers();

    User getUserById(Long id) throws NotFoundException;

    String uploadAvatar(MultipartFile uploadFile, String uploadPath);
}
