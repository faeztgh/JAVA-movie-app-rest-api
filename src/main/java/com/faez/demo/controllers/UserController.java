package com.faez.demo.controllers;

import com.faez.demo.models.User;
import com.faez.demo.services.UserServiceImpl;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.faez.demo.common.constants.AppConfig.UPLOAD_AVATAR_DIR;
import static com.faez.demo.routes.ApiRoute.*;
import static org.springframework.http.HttpStatus.CREATED;

/**
 * @author unknown
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserServiceImpl userService;

    @GetMapping(USERS_API)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPER_ADMIN')")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @GetMapping(GET_USER_BY_ID_API)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPER_ADMIN')")
    public User getUser(@PathVariable Long id) throws NotFoundException {
        return userService.getUserById(id);
    }

    @PostMapping(USERS_API)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path(USERS_API).toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    @PostMapping(UPLOAD_AVATAR_API)
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public Map<String, String> uploadAvatar(@RequestParam("avatar") MultipartFile uploadFile) {
        HashMap<String, String> map = new HashMap<>();
        map.put("status", String.valueOf(CREATED.value()));
        map.put("message", userService.uploadAvatar(uploadFile, UPLOAD_AVATAR_DIR));
        map.put("timestamp", new Date().toString());

        return map;
    }
}


