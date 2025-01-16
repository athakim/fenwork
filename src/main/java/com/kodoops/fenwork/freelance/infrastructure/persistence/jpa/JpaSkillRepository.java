package com.kodoops.fenwork.freelance.infrastructure.persistence.jpa;

import com.kodoops.fenwork.freelance.infrastructure.persistence.entity.SkillEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaSkillRepository extends JpaRepository<SkillEntity, String> {
}
