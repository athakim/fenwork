package com.kodoops.fenwork.freelance.presentation.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


public class PortfolioResponse {

    private String projectId;
    private String title;
    private String description;
    private String clientName;
    private LocalDate completionDate;

    // 🔥 Ajoute ce constructeur
    public PortfolioResponse(String projectId, String title, String description, String clientName, LocalDate completionDate) {
        this.projectId = projectId;
        this.title = title;
        this.description = description;
        this.clientName = clientName;
        this.completionDate = completionDate;
    }

    // 🔥 Ajoute aussi un constructeur sans arguments si nécessaire (pour Jackson)
    public PortfolioResponse() {}

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
}
