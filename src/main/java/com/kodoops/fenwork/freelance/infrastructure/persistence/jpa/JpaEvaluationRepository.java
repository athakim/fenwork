package com.kodoops.fenwork.freelance.infrastructure.persistence.jpa;

import com.kodoops.fenwork.freelance.infrastructure.persistence.entity.EvaluationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaEvaluationRepository extends JpaRepository<EvaluationEntity, String> {

}
