package com.example.sbnzproject;

import com.example.sbnzproject.models.*;
import com.example.sbnzproject.repositories.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@SpringBootApplication
public class SbnzProjectApplication implements CommandLineRunner {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ReadingProgressRepository readingProgressRepository;

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

        User admin = new Admin();
        admin.setId(2L);
        admin.setEmail("admin@mail.com");
        admin.setEnabled(true);
        admin.setFirstName("Admin");
        admin.setLastName("Adminic");
        admin.setPassword("sifra123");
        admin.setAuthorities(Arrays.asList(adminAuthority));
        userRepository.save(admin);

        // Create and save Category
        Category category = new Category();
        category.setId(1L);
        category.setActive(true);
        category.setName("Neka kategorija");
        category.setDescription("Kategorija");
        categoryRepository.save(category);

        // DateTimeFormatter for parsing dates
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Create and save Authors
        Author author1 = new Author(1L, "Agata Kristi", "Some description",
                LocalDateTime.parse("1900-08-07 00:00:00", formatter).toInstant(ZoneOffset.UTC),
                LocalDateTime.parse("1969-08-07 00:00:00", formatter).toInstant(ZoneOffset.UTC), 4.5, null);
        Author author2 = new Author(2L, "Donato Karizi", "Some description",
                LocalDateTime.parse("1900-08-07 00:00:00", formatter).toInstant(ZoneOffset.UTC),
                LocalDateTime.parse("1969-08-07 00:00:00", formatter).toInstant(ZoneOffset.UTC), 4.5, null);
        Author author3 = new Author(3L, "Fjodor Mihailovič Dostojevski", "Some description",
                LocalDateTime.parse("1900-08-07 00:00:00", formatter).toInstant(ZoneOffset.UTC),
                LocalDateTime.parse("1969-08-07 00:00:00", formatter).toInstant(ZoneOffset.UTC), 4.5, null);
        authorRepository.saveAll(Arrays.asList(author1, author2, author3));

        // Create and save Books
        Book book1 = new Book(1L, "Ubistvo u Mesopotamiji", "Some description", 0, 0, null, null, 164, category, new HashSet<>(Arrays.asList(author1)));
        Book book2 = new Book(2L, "Rani slučajevi Herkula Poaroa", "Some description", 4, 1, null, null, 336, category, new HashSet<>(Arrays.asList(author1)));
        Book book3 = new Book(3L, "Kukavičje jaje", "Some description", 0, 0, null, null, 272, category, new HashSet<>(Arrays.asList(author1)));
        Book book4 = new Book(4L, "Slonovi pamte", "Some description", 0, 0, null, null, 264, category, new HashSet<>(Arrays.asList(author1)));
        Book book5 = new Book(5L, "Ubistvo Rodžera Akrojda", "Some description", 0, 0, null, null, 288, category, new HashSet<>(Arrays.asList(author1)));
        Book book6 = new Book(6L, "Smrt na Nilu", "Some description", 0, 0, null, null, 306, category, new HashSet<>(Arrays.asList(author1)));
        Book book7 = new Book(7L, "Ubistvo u Orijent Ekspresu", "Some description", 0, 0, null, null, 254, category, new HashSet<>(Arrays.asList(author1)));
        Book book8 = new Book(8L, "Lovac na duše", "Some description", 5, 1, null, null, 319, category, new HashSet<>(Arrays.asList(author2)));
        Book book9 = new Book(9L, "Dečak od stakla", "Some description", 5, 1, null, null, 320, category, new HashSet<>(Arrays.asList(author2)));
        Book book10 = new Book(10L, "Vladar iz senke", "Some description", 0, 0, null, null, 332, category, new HashSet<>(Arrays.asList(author2)));
        Book book11 = new Book(11L, "Šaptač", "Some description", 0, 0, null, null, 360, category, new HashSet<>(Arrays.asList(author2)));
        Book book12 = new Book(12L, "Noć mi te uzima", "Some description", 0, 0, null, null, 296, category, new HashSet<>(Arrays.asList(author2)));
        Book book13 = new Book(13L, "Gospodar senki", "Some description", 0, 0, null, null, 264, category, new HashSet<>(Arrays.asList(author2)));
        Book book14 = new Book(14L, "Braća karamazovi I", "Some description", 0, 0, null, null, 458, category, new HashSet<>(Arrays.asList(author3)));
        Book book15 = new Book(15L, "Braća karamazovi II", "Some description", 0, 0, null, null, 586, category, new HashSet<>(Arrays.asList(author3)));
        Book book16 = new Book(16L, "Bele noći", "Some description", 0, 0, null, null, 70, category, new HashSet<>(Arrays.asList(author3)));
        Book book17 = new Book(17L, "Zapisi iz podezemlja", "Some description", 0, 0, null, null, 194, category, new HashSet<>(Arrays.asList(author3)));
        Book book18 = new Book(18L, "Idiot - I tom", "Some description", 1, 1, null, null, 490, category, new HashSet<>(Arrays.asList(author3)));
        Book book19 = new Book(19L, "Idiot - II tom", "Some description", 0, 0, null, null, 426, category, new HashSet<>(Arrays.asList(author3)));
        Book book20 = new Book(20L, "Zločin i kazna", "Some description", 0, 0, null, null, 714, category, new HashSet<>(Arrays.asList(author3)));
        Book book21 = new Book(21L, "Bedni ljudi", "Some description", 0, 0, null, null, 159, category, new HashSet<>(Arrays.asList(author3)));
        Book book22 = new Book(22L, "Kockar", "Some description", 0, 0, null, null, 189, category, new HashSet<>(Arrays.asList(author3)));
        bookRepository.saveAll(Arrays.asList(book1, book2, book3, book4, book5, book6, book7, book8, book9, book10, book11, book12, book13, book14, book15, book16, book17, book18, book19, book20, book21, book22));

        // Create and save Reading Progress
        ReadingProgress progress1 = new ReadingProgress(book11, (Reader)userRepository.findById(1L).orElse(null));
        progress1.setProgress(80);
        progress1.setLastOpened(LocalDateTime.parse("2021-05-23 12:20:00", formatter).toInstant(ZoneOffset.UTC));
        ReadingProgress progress2 = new ReadingProgress(book8, (Reader)userRepository.findById(1L).orElse(null));
        progress2.setProgress(310);
        progress2.setLastOpened(LocalDateTime.parse("2021-05-23 12:20:00", formatter).toInstant(ZoneOffset.UTC));

        ReadingProgress progress3 = new ReadingProgress(book18,(Reader) userRepository.findById(1L).orElse(null));
        progress3.setProgress(490);
        progress3.setLastOpened(LocalDateTime.parse("2021-05-23 12:20:00", formatter).toInstant(ZoneOffset.UTC));

        ReadingProgress progress4 = new ReadingProgress(book9, (Reader)userRepository.findById(1L).orElse(null));
        progress4.setProgress(310);
        progress4.setLastOpened(LocalDateTime.parse("2021-05-23 12:20:00", formatter).toInstant(ZoneOffset.UTC));

        readingProgressRepository.saveAll(Arrays.asList(progress1, progress2, progress3, progress4));
    }
}