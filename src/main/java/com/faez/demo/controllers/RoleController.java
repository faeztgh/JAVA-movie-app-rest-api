package com.faez.demo.controllers;

import com.faez.demo.dto.RoleToUserDto;
import com.faez.demo.models.Role;
import com.faez.demo.services.RoleServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static com.faez.demo.routes.ApiRoute.ADD_ROLE_TO_USER_API;
import static com.faez.demo.routes.ApiRoute.ROLE_API;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RoleController {
    private final RoleServiceImpl roleService;

    @PostMapping(ROLE_API)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPER_ADMIN')")
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users").toUriString());
        return ResponseEntity.created(uri).body(roleService.saveRole(role));
    }


    @PostMapping(ADD_ROLE_TO_USER_API)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPER_ADMIN')")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserDto form) {
        roleService.addRoleToUser(form.getUsername(), form.getRoleName());
        return ResponseEntity.ok().build();
    }

}
