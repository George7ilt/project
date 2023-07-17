package com.example.restapp.repo;

import com.example.restapp.models.Tattoo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TattooRepository extends JpaRepository<Tattoo, Long> {
}
