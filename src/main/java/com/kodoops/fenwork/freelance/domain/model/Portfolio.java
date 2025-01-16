package com.kodoops.fenwork.freelance.domain.model;

import java.time.LocalDate;
import java.util.UUID;

public class Portfolio {

    private String projectId;
    private String title;
    private String description;
    private String clientName;
    private LocalDate completionDate;
    private String freelanceId;


    public Portfolio(String projectId,
                     String title,
                     String description,
                     String clientName,
                     LocalDate completionDate,
                     String freelanceId
    ) {
        this.projectId = projectId;
        this.title = title;
        this.description = description;
        this.clientName = clientName;
        this.completionDate = completionDate;
        this.freelanceId = freelanceId;
    }

    public Portfolio(String title,
                     String description,
                     String clientName,
                     LocalDate completionDate,
                     String freelanceId
    ) {
        this.projectId = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.clientName = clientName;
        this.completionDate = completionDate;
        this.freelanceId = freelanceId;
    }

    // Getters
    public String getProjectId() {
        return projectId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getClientName() {
        return clientName;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public String getFreelanceId() {
        return freelanceId;
    }
    public void setFreelanceId(String freelanceId) {
        this.freelanceId = freelanceId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setCompletionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
    }
}
