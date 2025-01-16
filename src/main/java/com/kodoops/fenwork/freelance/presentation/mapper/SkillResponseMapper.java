package com.kodoops.fenwork.freelance.presentation.mapper;

import com.kodoops.fenwork.freelance.domain.model.Skill;
import com.kodoops.fenwork.freelance.presentation.response.SkillResponse;

import java.util.List;
import java.util.stream.Collectors;

public class SkillResponseMapper {

    public static List<SkillResponse> toResponseList(List<Skill> skills) {
        return skills.stream()
                .map(SkillResponseMapper::toResponse)
                .collect(Collectors.toList());
    }

    public static SkillResponse toResponse(Skill skill) {

        SkillResponse skillResponse = new SkillResponse();
        skillResponse.setId(skill.getId());
        skillResponse.setName(skill.getName());
        skillResponse.setLevel(skill.getLevel());
        skillResponse.setCategory(SkillCategoryResponseMapper.toResponse(skill.getCategory()));
        return skillResponse;
    }
}
