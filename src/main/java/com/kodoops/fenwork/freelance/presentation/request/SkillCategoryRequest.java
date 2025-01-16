package com.kodoops.fenwork.freelance.presentation.request;


public class SkillCategoryRequest {
    private String name;
    private String description;

    public SkillCategoryRequest( String name, String description) {
        this.name = name;
        this.description = description;
    }

    public SkillCategoryRequest() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}