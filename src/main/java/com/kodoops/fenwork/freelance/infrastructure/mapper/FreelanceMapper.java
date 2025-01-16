package com.kodoops.fenwork.freelance.infrastructure.mapper;

import com.kodoops.fenwork.freelance.domain.model.Evaluation;
import com.kodoops.fenwork.freelance.domain.model.Freelance;
import com.kodoops.fenwork.freelance.domain.model.Portfolio;
import com.kodoops.fenwork.freelance.domain.model.Skill;
import com.kodoops.fenwork.freelance.infrastructure.persistence.entity.*;

import java.util.List;

public class FreelanceMapper {

    public static Freelance toDomain(FreelanceEntity entity) {
        if (entity == null) {
            return null;
        }

        // Mapper les champs simples et les collections
        Freelance freelance = new Freelance(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getProfilePictureUrl(),
                entity.getBio(),
                entity.getAvailability(),
                entity.getTenantId()
        );

        // Mapper les compétences
        List<Skill> skills = entity.getSkills().stream()
                .map(SkillMapper::toDomain)
                .toList();
        freelance.getSkills().addAll(skills);

        // Mapper les portfolios
        List<Portfolio> portfolio = entity.getPortfolio().stream()
                .map(PortfolioMapper::toDomain)
                .toList();
        freelance.getPortfolio().addAll(portfolio);

        // Mapper les évaluations
        List<Evaluation> evaluations = entity.getEvaluations().stream()
                .map(EvaluationMapper::toDomain)
                .toList();
        freelance.getEvaluations().addAll(evaluations);

        return freelance;
    }

    public static FreelanceEntity toEntity(Freelance domain) {
        if (domain == null) {
            return null;
        }

        FreelanceEntity entity = new FreelanceEntity();
        entity.setId(domain.getId());
        entity.setFirstName(domain.getFirstName());
        entity.setLastName(domain.getLastName());
        entity.setEmail(domain.getEmail());
        entity.setProfilePictureUrl(domain.getProfilePictureUrl());
        entity.setBio(domain.getBio());
        entity.setAvailability(domain.getAvailability());
        entity.setTenantId(domain.getTenantId());

        // Mapper les compétences
        List<SkillEntity> skillEntities = domain.getSkills().stream()
                .map(skill -> SkillMapper.toEntity(skill, entity))
                .toList();
        entity.setSkills(skillEntities);

        // Mapper les portfolios
        List<PortfolioEntity> portfolioEntities = domain.getPortfolio().stream()
                .map(portfolio -> {
                    return PortfolioMapper.toEntity(portfolio, entity);
                })
                .toList();
        entity.setPortfolio(portfolioEntities);

        // Mapper les évaluations
        List<EvaluationEntity> evaluationEntities = domain.getEvaluations().stream()
                .map(evaluation -> {
                    return EvaluationMapper.toEntity(evaluation, entity);
                })
                .toList();
        entity.setEvaluations(evaluationEntities);

        return entity;
    }


}