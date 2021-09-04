package com.faez.demo;

import com.faez.demo.models.Movie;
import com.faez.demo.models.Role;
import com.faez.demo.models.User;
import com.faez.demo.services.MovieServiceImpl;
import com.faez.demo.services.RoleServiceImpl;
import com.faez.demo.services.UserServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

/**
 * @author unknown
 */
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(UserServiceImpl userService,
                          RoleServiceImpl roleService,
                          MovieServiceImpl movieService) {
        return args -> {

            userService.saveUser(new User(null, "Jhon Doe", "john", "1234", "jhon@gmail.com", new ArrayList<>()));
            userService.saveUser(new User(null, "Jane Doe", "jane", "1234", "jane@gmail.com", new ArrayList<>()));
            userService.saveUser(new User(null, "Jack Doe", "jack", "1234", "jack@gmail.com", new ArrayList<>()));
            userService.saveUser(new User(null, "Faran Doe", "faran", "1234", "faran@gmail.com", new ArrayList<>()));


            roleService.saveRole(new Role(null, "ROLE_USER"));
            roleService.saveRole(new Role(null, "ROLE_MANAGER"));
            roleService.saveRole(new Role(null, "ROLE_ADMIN"));
            roleService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));


            roleService.addRoleToUser("john", "ROLE_USER");
            roleService.addRoleToUser("john", "ROLE_MANAGER");
            roleService.addRoleToUser("jane", "ROLE_ADMIN");
            roleService.addRoleToUser("faran", "ROLE_USER");
            roleService.addRoleToUser("faran", "ROLE_ADMIN");


            // movies
            movieService.saveMovie(new Movie("title", "1999", "action", "50min"));
        };
    }
}
