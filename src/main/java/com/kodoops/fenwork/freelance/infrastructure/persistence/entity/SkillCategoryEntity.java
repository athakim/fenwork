package com.kodoops.fenwork.freelance.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "skills_categories")
public class SkillCategoryEntity {

    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    // Constructeur
    public SkillCategoryEntity(String id, String name, String description) {
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

    // Constructeur par défaut pour JPA
    protected SkillCategoryEntity() {}

    // Getters
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

    // Redéfinition de equals et hashCode pour garantir l'intégrité des objets
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SkillCategoryEntity that = (SkillCategoryEntity) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
