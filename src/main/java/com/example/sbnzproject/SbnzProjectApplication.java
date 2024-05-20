package com.example.sbnzproject;

import com.example.sbnzproject.models.Authority;
import com.example.sbnzproject.models.Reader;
import com.example.sbnzproject.models.User;
import com.example.sbnzproject.repositories.AuthorityRepository;

import com.example.sbnzproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@SpringBootApplication
public class SbnzProjectApplication implements CommandLineRunner {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    public static void main(String[] args) {
        SpringApplication.run(SbnzProjectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        initializeDatabase();
    }

    private void initializeDatabase() {
        // Create Authority objects
        Authority readerAuthority = new Authority();
        readerAuthority.setName("ROLE_READER");
        authorityRepository.save(readerAuthority);

        Authority adminAuthority = new Authority();
        adminAuthority.setName("ROLE_ADMIN");
        authorityRepository.save(adminAuthority);

        // Create User object
        User user = new Reader(); // Assuming Reader is a subclass of User
        user.setEmail("harry.gegic@gmail.com");
        user.setEnabled(true);
        user.setFirstName("Haris");
        user.setLastName("Gegic");
        user.setPassword("sifra123"); // Ensure password is encoded
        user.setAuthorities(List.of(readerAuthority)); // Set authorities
        userRepository.save(user);
    }
}