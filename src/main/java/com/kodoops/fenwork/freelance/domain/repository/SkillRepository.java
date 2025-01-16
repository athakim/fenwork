package com.kodoops.fenwork.freelance.domain.repository;

import com.kodoops.fenwork.freelance.domain.model.Skill;

import java.util.List;
import java.util.Optional;

public interface SkillRepository {
    Skill save(Skill skill);

    Optional<Skill> findById(String skillId);

    void deleteById(String skillId);

    List<Skill> findAll();
}
