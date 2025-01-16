package com.kodoops.fenwork.freelance.infrastructure.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "skills")
public class SkillEntity {

    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private SkillCategoryEntity category;

    @Column(nullable = false)
    private String level;

    @ManyToOne
    @JoinColumn(name = "freelance_id", nullable = false)
    private FreelanceEntity freelance;

    // Constructeur par défaut pour JPA
    public SkillEntity() {}

    // Constructeur avec arguments
    public SkillEntity(String id, String name, SkillCategoryEntity category, String level, FreelanceEntity freelance) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.level = level;
        this.freelance = freelance;
    }

    // Getters et setters
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

    public SkillCategoryEntity getCategory() {
        return category;
    }

    public void setCategory(SkillCategoryEntity category) {
        this.category = category;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public FreelanceEntity getFreelance() {
        return freelance;
    }

    public void setFreelance(FreelanceEntity freelance) {
        this.freelance = freelance;
    }

    // equals et hashCode basés sur id
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SkillEntity that = (SkillEntity) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}