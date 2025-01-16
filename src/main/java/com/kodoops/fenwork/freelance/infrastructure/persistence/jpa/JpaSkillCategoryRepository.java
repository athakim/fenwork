package com.kodoops.fenwork.freelance.infrastructure.persistence.jpa;

import com.kodoops.fenwork.freelance.infrastructure.persistence.entity.SkillCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaSkillCategoryRepository extends JpaRepository<SkillCategoryEntity, String> {
}
