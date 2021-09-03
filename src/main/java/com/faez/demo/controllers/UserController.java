package com.faez.demo.controllers;

import com.faez.demo.dto.RoleToUserDto;
import com.faez.demo.models.Role;
import com.faez.demo.models.User;
import com.faez.demo.services.UserServiceImpl;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.faez.demo.routes.ApiRoute.GET_USER_BY_ID_API;
import static com.faez.demo.routes.ApiRoute.USERS_API;

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
    @PreAuthorize("hasAuthority('admin:write')")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }



}


