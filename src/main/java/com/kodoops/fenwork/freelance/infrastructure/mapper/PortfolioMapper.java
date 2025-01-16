package com.kodoops.fenwork.freelance.infrastructure.mapper;

import com.kodoops.fenwork.freelance.domain.model.Portfolio;
import com.kodoops.fenwork.freelance.infrastructure.persistence.entity.FreelanceEntity;
import com.kodoops.fenwork.freelance.infrastructure.persistence.entity.PortfolioEntity;

public class PortfolioMapper {
    public static Portfolio toDomain(PortfolioEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Portfolio(
                entity.getProjectId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getClientName(),
                entity.getCompletionDate(),
                entity.getFreelance().getId()
        );
    }

    public static PortfolioEntity toEntity(Portfolio domain, FreelanceEntity freelanceEntity) {
        if (domain == null) {
            return null;
        }
        PortfolioEntity entity = new PortfolioEntity();
        entity.setProjectId(domain.getProjectId());
        entity.setTitle(domain.getTitle());
        entity.setDescription(domain.getDescription());
        entity.setClientName(domain.getClientName());
        entity.setCompletionDate(domain.getCompletionDate());
        if (freelanceEntity != null) {
            entity.setFreelance(freelanceEntity);
        } else {
            throw new IllegalArgumentException("FreelanceEntity ne peut pas être null !");
        }
        return entity;
    }
}
