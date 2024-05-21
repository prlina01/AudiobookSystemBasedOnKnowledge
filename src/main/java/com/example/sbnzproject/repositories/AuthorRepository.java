package com.example.sbnzproject.repositories;

import com.example.sbnzproject.models.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    Page<Author> findByNameContainingIgnoreCase(String name, Pageable pageable);

    @Query("select distinct authors from ReadingProgress rp join rp.book b join b.authors authors where rp.reader.id = :userId")
    List<Author> getReadAuthors(long userId);
}