package com.kodoops.fenwork.freelance.presentation.request;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


public class PortfolioRequest {

    @NotBlank(message = "Project title is required")
    private String title;
    @NotBlank(message = "Project description is required")
    private String description;
    @NotBlank(message = "Project client name is required")
    private String clientName;
    @NotNull(message = "Project completion date is required")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate completionDate;


    public PortfolioRequest() {
    }

    public PortfolioRequest(String title, String description, String clientName, LocalDate completionDate) {
        this.title = title;
        this.description = description;
        this.clientName = clientName;
        this.completionDate = completionDate;
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
}
