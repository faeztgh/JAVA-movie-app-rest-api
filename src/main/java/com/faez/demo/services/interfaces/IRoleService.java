package com.faez.demo.services.interfaces;

import com.faez.demo.models.Role;

public interface IRoleService {

    Role saveRole(Role role);

    void addRoleToUser(String username, String roleName);
}
