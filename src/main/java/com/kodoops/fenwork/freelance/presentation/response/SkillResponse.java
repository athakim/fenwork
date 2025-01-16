package com.kodoops.fenwork.freelance.presentation.response;


public class SkillResponse {

    private String id;
    private String name;
    private String level;
    private SkillCategory category;

    public SkillResponse() {
    }

    public SkillResponse(String id, String name, String level, SkillCategory category) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public SkillCategory getCategory() {
        return category;
    }

    public void setCategory(SkillCategory category) {
        this.category = category;
    }
}
