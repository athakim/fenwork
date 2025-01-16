package com.kodoops.fenwork.freelance.domain.model;

import java.time.LocalDateTime;

public class Evaluation {

    private String evaluationId;
    private int rating; // 1 à 5
    private String comment;
    private LocalDateTime createdAt;
    private String projectId; // Relation avec un projet dans le portfolio
    private String freelanceId;

    public Evaluation(String evaluationId, int rating, String comment, LocalDateTime createdAt, String projectId) {
        this.evaluationId = evaluationId;
        this.rating = rating;
        this.comment = comment;
        this.createdAt = createdAt;
        this.projectId = projectId;
    }
    public Evaluation(String evaluationId, int rating, String comment, LocalDateTime createdAt, String projectId, String freelanceId) {
        this.evaluationId = evaluationId;
        this.rating = rating;
        this.comment = comment;
        this.createdAt = createdAt;
        this.projectId = projectId;
        this.freelanceId = freelanceId;
    }

    public String getEvaluationId() {
        return evaluationId;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getProjectId() {
        return projectId;
    }

    public String getFreelanceId() {
        return freelanceId;
    }

    public void setFreelanceId(String id) {
        this.evaluationId = id;
    }
}
