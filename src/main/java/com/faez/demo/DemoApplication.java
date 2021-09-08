package com.faez.demo;

import com.faez.demo.movie.Movie;
import com.faez.demo.movie.MovieServiceImpl;
import com.faez.demo.quote.Quote;
import com.faez.demo.quote.QuoteServiceImpl;
import com.faez.demo.role.Role;
import com.faez.demo.role.RoleServiceImpl;
import com.faez.demo.user.User;
import com.faez.demo.user.UserServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.ArrayList;

/**
 * @author unknown
 */
@SpringBootApplication
@EnableTransactionManagement
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
                          MovieServiceImpl movieService,
                          QuoteServiceImpl quoteService
    ) {
        return args -> {


            roleService.saveRole(new Role("ROLE_USER"));
            roleService.saveRole(new Role("ROLE_MANAGER"));
            roleService.saveRole(new Role("ROLE_ADMIN"));
            roleService.saveRole(new Role("ROLE_SUPER_ADMIN"));

            userService.saveUser(new User(null, "Jhon", "Doe", "john", "1234", "jhon@gmail.com", new ArrayList<>()));
            userService.saveUser(new User(null, "Jane", "Doe", "jane", "1234", "jane@gmail.com", new ArrayList<>()));
            userService.saveUser(new User(null, "Jack", "Doe", "jack", "1234", "jack@gmail.com", new ArrayList<>()));
            userService.saveUser(new User(null, "Faran", "Doe", "faran", "1234", "faran@gmail.com", new ArrayList<>()));

//
            roleService.addRoleToUser("john", "ROLE_USER");
            roleService.addRoleToUser("jane", "ROLE_ADMIN");
            roleService.addRoleToUser("faran", "ROLE_USER");
            roleService.addRoleToUser("faran", "ROLE_ADMIN");


//            // movies
            Movie movie1 = movieService.saveMovie(new Movie("title", "1999", "action", "50min"));

            // quotes
            quoteService.saveQuote(new Quote(
                    "Lucifer",
                    "Tom Elis",
                    "You send yourselves. Driven down by your own guilt. Forcing yourselves to relive your sins over and over. And the best part…the doors aren’t locked. You could leave anytime. It says something that no one ever does, doesn’t it?",
                    movie1
            ));


        };
    }

}
