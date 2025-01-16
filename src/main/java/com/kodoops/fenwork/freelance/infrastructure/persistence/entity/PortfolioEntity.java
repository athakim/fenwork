package com.kodoops.fenwork.freelance.infrastructure.persistence.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "porfolios")
public class PortfolioEntity {
    @Id
    private String projectId;

    private String title;
    private String description;
    private String clientName;
    private LocalDate completionDate;

    @ManyToOne
    @JoinColumn(name = "freelance_id", nullable = false)
    private FreelanceEntity freelance;


    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
    }

    public FreelanceEntity getFreelance() {
        return freelance;
    }

    public void setFreelance(FreelanceEntity freelance) {
        this.freelance = freelance;
    }
}
