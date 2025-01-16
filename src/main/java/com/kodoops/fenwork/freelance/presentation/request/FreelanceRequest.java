package com.kodoops.fenwork.freelance.presentation.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
public class FreelanceRequest {

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Tenant Id is required")
    private String tenantId;

    private String profilePictureUrl;
    private String bio;
    private String availability;

    public FreelanceRequest() {
    }

    public FreelanceRequest(String firstName, String lastName, String email, String tenantId, String profilePictureUrl, String bio, String availability) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.tenantId = tenantId;
        this.profilePictureUrl = profilePictureUrl;
        this.bio = bio;
        this.availability = availability;
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

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
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
}
