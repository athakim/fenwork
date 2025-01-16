package com.kodoops.fenwork.freelance.domain.repository;

import com.kodoops.fenwork.freelance.domain.model.Freelance;

import java.util.List;
import java.util.Optional;

public interface FreelanceRepository {

    Freelance save(Freelance freelance);

    Optional<Freelance> findById(String id);

    List<Freelance> findAll();

    void deleteFreelance(String id);
}
