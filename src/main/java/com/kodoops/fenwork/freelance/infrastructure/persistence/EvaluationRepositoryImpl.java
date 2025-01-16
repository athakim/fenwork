package com.kodoops.fenwork.freelance.infrastructure.persistence;

import com.kodoops.fenwork.freelance.domain.exception.FreelanceException;
import com.kodoops.fenwork.freelance.domain.model.Evaluation;
import com.kodoops.fenwork.freelance.domain.repository.EvaluationRepository;
import com.kodoops.fenwork.freelance.infrastructure.mapper.EvaluationMapper;
import com.kodoops.fenwork.freelance.infrastructure.persistence.entity.EvaluationEntity;
import com.kodoops.fenwork.freelance.infrastructure.persistence.entity.FreelanceEntity;
import com.kodoops.fenwork.freelance.infrastructure.persistence.jpa.JpaEvaluationRepository;
import com.kodoops.fenwork.freelance.infrastructure.persistence.jpa.JpaFreelanceRepository;
import org.springframework.stereotype.Repository;

@Repository
public class EvaluationRepositoryImpl implements EvaluationRepository {

    private final JpaEvaluationRepository jpaEvaluationRepository;
    private final JpaFreelanceRepository jpaFreelanceRepository;
    public EvaluationRepositoryImpl(JpaEvaluationRepository jpaEvaluationRepository, JpaFreelanceRepository jpaFreelanceRepository) {
        this.jpaEvaluationRepository = jpaEvaluationRepository;
        this.jpaFreelanceRepository = jpaFreelanceRepository;
    }

    @Override
    public Evaluation save(Evaluation evaluation) {
        FreelanceEntity freelanceEntity = jpaFreelanceRepository.findById(evaluation.getFreelanceId())
                .orElseThrow(() -> new FreelanceException("Freelance non disponible."));

        EvaluationEntity evaluationEntity = jpaEvaluationRepository.save(EvaluationMapper.toEntity(evaluation,freelanceEntity));
        return EvaluationMapper.toDomain(evaluationEntity);
    }
}
