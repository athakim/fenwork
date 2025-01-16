package com.kodoops.fenwork.freelance.infrastructure.mapper;

import com.kodoops.fenwork.freelance.domain.model.Skill;
import com.kodoops.fenwork.freelance.infrastructure.persistence.entity.FreelanceEntity;
import com.kodoops.fenwork.freelance.infrastructure.persistence.entity.SkillEntity;

public class SkillMapper {
    public static Skill toDomain(SkillEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Skill(
                entity.getId(),
                entity.getName(),
                SkillCategoryMapper.toDomain(entity.getCategory()),
                entity.getLevel(),
                entity.getFreelance() != null ? entity.getFreelance().getId() : null
        );
    }

    public static SkillEntity toEntity(Skill domain, FreelanceEntity freelanceEntity) {
        if (domain == null) {
            return null;
        }
        SkillEntity entity = new SkillEntity();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setCategory(SkillCategoryMapper.toEntity(domain.getCategory()));
        entity.setLevel(domain.getLevel());
        entity.setFreelance(freelanceEntity);
        return entity;
    }
}
