package com.kodoops.fenwork.freelance.infrastructure.mapper;

import com.kodoops.fenwork.freelance.domain.model.Evaluation;
import com.kodoops.fenwork.freelance.infrastructure.persistence.entity.EvaluationEntity;
import com.kodoops.fenwork.freelance.infrastructure.persistence.entity.FreelanceEntity;

public class EvaluationMapper {

    public static Evaluation toDomain(EvaluationEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Evaluation(
                entity.getEvaluationId(),
                entity.getRating(),
                entity.getComment(),
                entity.getCreatedAt(),
                entity.getProjectId(),
                entity.getFreelance().getId()
        );
    }

    public static EvaluationEntity toEntity(Evaluation domain, FreelanceEntity freelanceEntity) {
        if (domain == null) {
            return null;
        }
        EvaluationEntity entity = new EvaluationEntity();
        entity.setEvaluationId(domain.getEvaluationId());
        entity.setRating(domain.getRating());
        entity.setComment(domain.getComment());
        entity.setCreatedAt(domain.getCreatedAt());
        entity.setProjectId(domain.getProjectId());
        if (freelanceEntity != null) {
            entity.setFreelance(freelanceEntity);
        } else {
            throw new IllegalArgumentException("FreelanceEntity ne peut pas être null !");
        }
        return entity;
    }
}
