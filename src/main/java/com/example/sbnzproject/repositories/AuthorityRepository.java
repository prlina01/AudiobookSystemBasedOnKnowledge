package com.example.sbnzproject.repositories;

import com.example.sbnzproject.models.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}