package com.kodoops.fenwork.freelance.presentation.mapper;

import com.kodoops.fenwork.freelance.domain.model.SkillCategory;
import com.kodoops.fenwork.freelance.presentation.response.SkillCategoryResponse;

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
}