package com.example.restapp.repo;

import com.example.restapp.models.Master;
import com.example.restapp.models.Studio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository

public interface MasterRepo extends JpaRepository<Master, Long> {
    Optional<Master> findByName(String name);

    List<Master> findAllBySpecialization(String specialization);

    List<Master> findAllByExperienceYearsGreaterThan(int experience);

    List<Master> findAllByStudioAndSpecialization(Studio studio, String spec);
}
