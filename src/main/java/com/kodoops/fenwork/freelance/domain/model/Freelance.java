package com.kodoops.fenwork.freelance.domain.model;


import com.kodoops.fenwork.freelance.domain.vo.Availability;

import java.util.ArrayList;
import java.util.List;

public class Freelance {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String profilePictureUrl;
    private String bio;
    private List<Skill> skills = new ArrayList<>(); // Utilisation de Skill comme modèle
    private Availability availability;
    private String tenantId;
    private List<Portfolio> portfolio = new ArrayList<>();
    private List<Evaluation> evaluations = new ArrayList<>(); // Stockage des évaluations


    public Freelance(String id, String firstName, String lastName, String email, String profilePictureUrl,
                     String bio, Availability availability, String tenantId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.profilePictureUrl = profilePictureUrl;
        this.bio = bio;
        this.availability = availability;
        this.tenantId = tenantId;
    }

    public void addSkill(Skill skill) {
        if (skills == null) {
            skills = new ArrayList<>();
        }
        skills.add(skill);
        skill.setFreelanceId(this.id);
    }

    public void removeSkill(Skill skill) {
        skills.remove(skill);
    }

    public void addProject(Portfolio project) {
        if (portfolio == null) {
            portfolio = new ArrayList<>();
        }
        project.setFreelanceId(this.id);
        portfolio.add(project);
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public String getBio() {
        return bio;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public Availability getAvailability() {
        return availability;
    }

    public String getTenantId() {
        return tenantId;
    }

    public List<Portfolio> getPortfolio() {
        return portfolio;
    }

    public void updateInfo(String firstName, String lastName, String email, String profilePictureUrl, String bio) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.profilePictureUrl = profilePictureUrl;
        this.bio = bio;
    }


    public void addEvaluation(Evaluation evaluation) {
        boolean projectExists = portfolio.stream()
                .anyMatch(p -> p.getProjectId().equals(evaluation.getProjectId()));

        if (!projectExists) {
            throw new IllegalArgumentException("Project with ID " + evaluation.getProjectId() + " not found in portfolio");
        }

        evaluations.add(evaluation);
    }

    public List<Evaluation> getEvaluations() {
        return evaluations;
    }

    public void setPortfolio(List<Portfolio> updatedPortfolios) {
        this.portfolio = updatedPortfolios;
    }
}
