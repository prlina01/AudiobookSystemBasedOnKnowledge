package com.example.sbnzproject.repositories;

import com.example.sbnzproject.models.ReadingProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReadingProgressRepository extends JpaRepository<ReadingProgress, Long> {

    long countByBookId(long bookId);

    List<ReadingProgress> getByBookId(long bookId);

    List<ReadingProgress> getByReaderId(long readerId);

    Optional<ReadingProgress> findByBookIdAndReaderId(long bookId, long readerId);

    @Query("select rp from ReadingProgress rp where rp.reader.id = :readerId or rp.reader.male = :isMale")
    List<ReadingProgress> getReadingProgressByReaderIdAndReaderCategory(long readerId, boolean isMale);
}