package com.kodoops.fenwork.freelance.domain.repository;

import com.kodoops.fenwork.freelance.domain.model.SkillCategory;

import java.util.List;
import java.util.Optional;

public interface SkillCategoryRepository {
    Optional<SkillCategory> findById(String categoryId);

    List<SkillCategory> findAll();

    SkillCategory save(SkillCategory category);

    void deleteById(String id);
}
