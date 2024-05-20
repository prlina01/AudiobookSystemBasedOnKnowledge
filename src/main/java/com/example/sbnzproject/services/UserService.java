package com.example.sbnzproject.services;

import com.example.sbnzproject.models.Reader;
import com.example.sbnzproject.models.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.sbnzproject.repositories.UserRepository;

import java.util.Date;


@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final MailService mailService;

@Autowired
    public UserService(UserRepository userRepository, MailService mailService) {
        this.userRepository = userRepository;
        this.mailService = mailService;
    }

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("No user found with email " + email));
    }

    @Transactional
    public void changePassword(String oldPassword, String newPassword) throws IllegalAccessException {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String username;
        try {
            username = ((User) currentUser.getPrincipal()).getEmail();
        } catch (Exception e) {
            throw new IllegalAccessException("Invalid token.");
        }

        User user = loadUserByUsername(username);
        user.setPassword(new BCryptPasswordEncoder().encode(newPassword));
        user.setLastPasswordResetDate(new Date().getTime());
        userRepository.save(user);
    }

    @Transactional
    public User changeProfile(User entity) throws Exception {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!entity.getEmail().equals(user.getEmail())) {
            if (userRepository.findByEmail(entity.getEmail()).isPresent()) {
                throw new IllegalArgumentException("Username already taken");
            }
        }
        user.setEmail(entity.getEmail());
        user.setFirstName(entity.getFirstName());
        user.setLastName(entity.getLastName());
        return userRepository.save(user);
    }

    public Iterable<User> getAll() {
        return null;
    }

    public User getById(Long id) {
        return null;
    }

    public User create(User entity) throws Exception {
        return null;
    }

    public boolean delete(Long id) throws Exception {
        return false;
    }

    public Reader update(Long id, Reader entity) throws Exception {
        return null;
    }

    public User update(Long id, User entity) throws Exception {
        return null;
    }

    public User findByEmail(String email) {
        return this.loadUserByUsername(email);
    }
}