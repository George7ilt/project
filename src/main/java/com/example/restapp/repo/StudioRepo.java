package com.example.restapp.repo;

import com.example.restapp.models.Studio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudioRepo extends JpaRepository<Studio, Long> {
}
