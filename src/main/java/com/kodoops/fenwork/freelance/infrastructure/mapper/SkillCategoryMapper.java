package com.kodoops.fenwork.freelance.infrastructure.mapper;

import com.kodoops.fenwork.freelance.domain.model.SkillCategory;
import com.kodoops.fenwork.freelance.infrastructure.persistence.entity.SkillCategoryEntity;

public class SkillCategoryMapper {


    public static SkillCategory toDomain(SkillCategoryEntity entity) {
        if (entity == null) {
            return null;
        }
        return new SkillCategory(entity.getId(), entity.getName(), entity.getDescription());
    }

    public static SkillCategoryEntity toEntity(SkillCategory domain) {
        if (domain == null) {
            return null;
        }
        return new SkillCategoryEntity(domain.getId(), domain.getName(), domain.getDescription());
    }

}
