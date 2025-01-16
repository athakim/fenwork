package com.kodoops.fenwork.freelance.presentation.response;

import lombok.*;

import java.time.LocalDateTime;


public class EvaluationResponse {

    private String evaluationId;
    private int rating;
    private String comment;
    private LocalDateTime createdAt;
    private String projectId;


    public EvaluationResponse() {
    }

    public EvaluationResponse(String evaluationId, int rating, String comment, LocalDateTime createdAt, String projectId) {
        this.evaluationId = evaluationId;
        this.rating = rating;
        this.comment = comment;
        this.createdAt = createdAt;
        this.projectId = projectId;
    }

    public String getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(String evaluationId) {
        this.evaluationId = evaluationId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
