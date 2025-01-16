package com.kodoops.fenwork.freelance.presentation.request;

import jakarta.validation.constraints.NotBlank;



public class SkillRequest {

    @NotBlank(message = "Skill name is required")
    private String name;

    @NotBlank(message = "Skill category ID is required")
    private String categoryId;

    private String level; // Beginner, Intermediate, Expert

    public SkillRequest() {
    }

    public SkillRequest(String name, String categoryId, String level) {
        this.name = name;
        this.categoryId = categoryId;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getLevel() {
        return level;
    }
}
