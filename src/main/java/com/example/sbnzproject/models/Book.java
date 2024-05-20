package com.example.sbnzproject.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Lob
//    @Type(type = "org.hibernate.type.TextType")
    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private double averageRating;

    private long numReviews;

    private String cover;

    private String pdf;

    @Column(nullable = false)
//    private BookType type;

    //ex quantity -> seconds
    private long duration;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Author> authors;

}