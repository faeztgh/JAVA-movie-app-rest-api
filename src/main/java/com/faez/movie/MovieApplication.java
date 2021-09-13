package com.faez.movie;

import com.faez.movie.movie.Movie;
import com.faez.movie.movie.MovieServiceImpl;
import com.faez.movie.quote.Quote;
import com.faez.movie.quote.QuoteServiceImpl;
import com.faez.movie.role.Role;
import com.faez.movie.role.RoleServiceImpl;
import com.faez.movie.user.User;
import com.faez.movie.user.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.ArrayList;
import java.util.List;

/**
 * @author unknown
 */
@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
@Slf4j
public class MovieApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieApplication.class, args);
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

            // movies
            Movie movie1 = movieService.saveMovie(new Movie("title", "1999", "action", "50min"));
            Movie movie2 = movieService.saveMovie(new Movie("title2", "2012", "drama", "50min"));
            Movie movie3 = movieService.saveMovie(new Movie("title3", "1950", "document", "50min"));

            // movie list
            List<Movie> movieList1 = new ArrayList<>();
            movieList1.add(movie1);
            movieList1.add(movie2);

            userService.saveUser(new User(null, "Jhon", "Doe", "jhon", "1234", "jhon@gmail.com", new ArrayList<>(), movieList1));
            userService.saveUser(new User(null, "Jane", "Doe", "jane", "1234", "jane@gmail.com", new ArrayList<>(), null));
            userService.saveUser(new User(null, "Jack", "Doe", "jack", "1234", "jack@gmail.com", new ArrayList<>(), null));
            userService.saveUser(new User(null, "Faran", "Doe", "faran", "1234", "d.jkh98@gmail.com", new ArrayList<>(), null));

            roleService.saveRole(new Role("ROLE_USER"));
            roleService.saveRole(new Role("ROLE_MANAGER"));
            roleService.saveRole(new Role("ROLE_ADMIN"));
            roleService.saveRole(new Role("ROLE_SUPER_ADMIN"));

            roleService.addRoleToUser("jhon", "ROLE_USER");
            roleService.addRoleToUser("jane", "ROLE_ADMIN");
            roleService.addRoleToUser("faran", "ROLE_USER");
            roleService.addRoleToUser("faran", "ROLE_ADMIN");


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
