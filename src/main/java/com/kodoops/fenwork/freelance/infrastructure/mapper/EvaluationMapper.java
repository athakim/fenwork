package com.kodoops.fenwork.freelance.infrastructure.mapper;

import com.kodoops.fenwork.freelance.domain.model.Evaluation;
import com.kodoops.fenwork.freelance.infrastructure.persistence.entity.EvaluationEntity;

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
                entity.getProjectId()
        );
    }

    public static EvaluationEntity toEntity(Evaluation domain) {
        if (domain == null) {
            return null;
        }
        EvaluationEntity entity = new EvaluationEntity();
        entity.setEvaluationId(domain.getEvaluationId());
        entity.setRating(domain.getRating());
        entity.setComment(domain.getComment());
        entity.setCreatedAt(domain.getCreatedAt());
        entity.setProjectId(domain.getProjectId());
        return entity;
    }
}
