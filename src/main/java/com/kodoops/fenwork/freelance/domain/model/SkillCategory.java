package com.kodoops.fenwork.freelance.domain.model;

import java.util.Objects;

public class SkillCategory {

    private final String id;
    private final String name;
    private final String description;

    public SkillCategory(String id, String name, String description) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Category ID cannot be null or blank");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Category name cannot be null or blank");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Category description cannot be null or blank");
        }
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    // Méthode utilitaire
    public String fullDescription() {
        return name + ": " + description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SkillCategory that = (SkillCategory) o;
        return id.equals(that.id) && name.equals(that.name) && description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }

    @Override
    public String toString() {
        return "SkillCategory{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}