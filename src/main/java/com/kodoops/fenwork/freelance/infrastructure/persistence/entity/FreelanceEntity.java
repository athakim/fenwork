package com.kodoops.fenwork.freelance.infrastructure.persistence.entity;

import com.kodoops.fenwork.freelance.domain.vo.Availability;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "freelnces")
public class FreelanceEntity {
    @Id
    private String id;

    private String firstName;
    private String lastName;
    private String email;
    private String profilePictureUrl;
    private String bio;
    private String tenantId;

    @Enumerated(EnumType.STRING)
    private Availability availability;

    @OneToMany(mappedBy = "freelance", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SkillEntity> skills = new ArrayList<>();

    @OneToMany(mappedBy = "freelance", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PortfolioEntity> portfolio = new ArrayList<>();

    @OneToMany(mappedBy = "freelance", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EvaluationEntity> evaluations = new ArrayList<>();

    // Constructeur, getters et setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public Availability getAvailability() {
        return availability;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }

    public List<SkillEntity> getSkills() {
        return skills;
    }

    public void setSkills(List<SkillEntity> skills) {
        this.skills = skills;
    }

    public List<PortfolioEntity> getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(List<PortfolioEntity> portfolio) {
        this.portfolio = portfolio;
    }

    public List<EvaluationEntity> getEvaluations() {
        return evaluations;
    }

    public void setEvaluations(List<EvaluationEntity> evaluations) {
        this.evaluations = evaluations;
    }
}
