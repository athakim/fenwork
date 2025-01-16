package com.kodoops.fenwork.freelance.domain.model;


import java.util.Objects;

public class Skill {
    private String id;
    private String name;
    private SkillCategory category; // Utilisation de SkillCategory
    private String level; // Beginner, Intermediate, Expert
    private String freelanceId;

    // Constructeur
    public Skill(String id, String name, SkillCategory category, String level, String freelanceId) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Skill name cannot be null or blank");
        }
        this.id = id;
        this.name = name;
        this.category = category;
        this.level = level;
        this.freelanceId = freelanceId;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public SkillCategory getCategory() {
        return category;
    }

    public String getLevel() {
        return level;
    }

    public String getFreelanceId() {
        return freelanceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skill skill = (Skill) o;
        return Objects.equals(id, skill.id) &&
                Objects.equals(name, skill.name) &&
                Objects.equals(category, skill.category) &&
                Objects.equals(level, skill.level) &&
                Objects.equals(freelanceId, skill.freelanceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, category, level, freelanceId);
    }

    public void setFreelanceId(String freelanceId) {
       this.freelanceId = freelanceId;
    }
}
