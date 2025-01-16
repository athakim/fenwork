package com.kodoops.fenwork.freelance.presentation.mapper;

import com.kodoops.fenwork.freelance.domain.model.Portfolio;
import com.kodoops.fenwork.freelance.domain.model.SkillCategory;
import com.kodoops.fenwork.freelance.presentation.response.PortfolioResponse;
import com.kodoops.fenwork.freelance.presentation.response.SkillCategoryResponse;

import java.util.List;
import java.util.stream.Collectors;

public class SkillCategoryResponseMapper {

    public static SkillCategoryResponse toResponse(SkillCategory domain) {
        if (domain == null) {
            return null;
        }

        return new SkillCategoryResponse(
                domain.getId(),
               domain.getName(),
               domain.getDescription()
           );
    }

    public static List<SkillCategoryResponse> toResponseList(List<SkillCategory> skillCategories) {
        return skillCategories.stream()
                .map(SkillCategoryResponseMapper::toResponse)
                .collect(Collectors.toList());
    }
}