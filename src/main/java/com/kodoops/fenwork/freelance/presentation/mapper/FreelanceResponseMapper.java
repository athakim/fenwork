package com.kodoops.fenwork.freelance.presentation.mapper;

import com.kodoops.fenwork.freelance.domain.model.Evaluation;
import com.kodoops.fenwork.freelance.domain.model.Freelance;
import com.kodoops.fenwork.freelance.domain.model.Portfolio;
import com.kodoops.fenwork.freelance.domain.model.Skill;
import com.kodoops.fenwork.freelance.domain.model.SkillCategory;
import com.kodoops.fenwork.freelance.presentation.response.*;

import java.util.List;
import java.util.stream.Collectors;

public class FreelanceResponseMapper {

    public static FreelanceResponse toResponse(Freelance freelance) {
        if (freelance == null) {
            return null;
        }

        return new FreelanceResponse(
                freelance.getId(),
                freelance.getFirstName(),
                freelance.getLastName(),
                freelance.getEmail(),
                freelance.getProfilePictureUrl(),
                freelance.getBio(),
                freelance.getAvailability().name(), // Convert enum to String
                freelance.getTenantId(),
                mapSkills(freelance.getSkills()),
                mapPortfolio(freelance.getPortfolio()),
                mapEvaluations(freelance.getEvaluations())
        );
    }

    private static List<SkillResponse> mapSkills(List<Skill> skills) {
        if (skills == null) {
            return List.of();
        }

        return skills.stream()
                .map(skill -> new SkillResponse(
                        skill.getId(),
                        skill.getName(),
                        skill.getCategory().getName(),
                        mapSkillCategory(skill.getCategory())
                ))
                .collect(Collectors.toList());
    }

    private static List<PortfolioResponse> mapPortfolio(List<Portfolio> portfolio) {
        if (portfolio == null) {
            return List.of();
        }

        return portfolio.stream()
                .map(project -> new PortfolioResponse(
                        project.getProjectId(),
                        project.getTitle(),
                        project.getDescription(),
                        project.getClientName(),
                        project.getCompletionDate()
                ))
                .collect(Collectors.toList());
    }

    private static List<EvaluationResponse> mapEvaluations(List<Evaluation> evaluations) {
        if (evaluations == null) {
            return List.of();
        }

        return evaluations.stream()
                .map(evaluation -> new EvaluationResponse(
                        evaluation.getEvaluationId(),
                        evaluation.getRating(),
                        evaluation.getComment(),
                        evaluation.getCreatedAt(),
                        evaluation.getProjectId()
                ))
                .collect(Collectors.toList());
    }


    private static com.kodoops.fenwork.freelance.presentation.response.SkillCategory mapSkillCategory(SkillCategory category) {
        if (category == null) {
            return null;
        }

        return new com.kodoops.fenwork.freelance.presentation.response.SkillCategory(
              category.getId(),
                category.getName(),
                category.getDescription());
    }



    public static List<FreelanceResponse> toResponseList(List<Freelance> freelances) {
        return freelances.stream()
                .map(FreelanceResponseMapper::toResponse)
                .collect(Collectors.toList());
    }
}