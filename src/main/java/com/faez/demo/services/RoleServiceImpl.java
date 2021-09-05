package com.faez.demo.services;

import com.faez.demo.exceptions.ApiRequestException;
import com.faez.demo.models.Role;
import com.faez.demo.models.User;
import com.faez.demo.repositories.RoleRepository;
import com.faez.demo.repositories.UserRepository;
import com.faez.demo.services.interfaces.IRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class RoleServiceImpl implements IRoleService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to the Database", role.getName());
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {}", roleName, username);

        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);

        if (user == null || role == null) {
            throw new ApiRequestException("Role or User not exist!");
        }
        user.getRoles().add(role);
    }
}
