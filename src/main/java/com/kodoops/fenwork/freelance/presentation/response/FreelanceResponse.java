package com.kodoops.fenwork.freelance.presentation.response;

import java.util.List;

public class FreelanceResponse {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String profilePictureUrl;
    private String bio;
    private String availability;
    private String tenantId;
    private List<SkillResponse> skills;
    private List<PortfolioResponse> portfolio;
    private List<EvaluationResponse> evaluations;

    // Constructeur avec tous les arguments
    public FreelanceResponse(String id, String firstName, String lastName, String email, String profilePictureUrl,
                             String bio, String availability, String tenantId, List<SkillResponse> skills,
                             List<PortfolioResponse> portfolio, List<EvaluationResponse> evaluations) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.profilePictureUrl = profilePictureUrl;
        this.bio = bio;
        this.availability = availability;
        this.tenantId = tenantId;
        this.skills = skills;
        this.portfolio = portfolio;
        this.evaluations = evaluations;
    }


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

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public List<SkillResponse> getSkills() {
        return skills;
    }

    public void setSkills(List<SkillResponse> skills) {
        this.skills = skills;
    }

    public List<PortfolioResponse> getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(List<PortfolioResponse> portfolio) {
        this.portfolio = portfolio;
    }

    public List<EvaluationResponse> getEvaluations() {
        return evaluations;
    }

    public void setEvaluations(List<EvaluationResponse> evaluations) {
        this.evaluations = evaluations;
    }
}
