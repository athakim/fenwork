package com.kodoops.fenwork.freelance.infrastructure.persistence.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "evaluations")
public class EvaluationEntity {
    @Id
    private String evaluationId;

    private int rating;
    private String comment;
    private LocalDateTime createdAt;

    private String projectId;

    @ManyToOne
    @JoinColumn(name = "freelance_id", nullable = false)
    private FreelanceEntity freelance;

    // Constructeur, getters et setters


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

    public FreelanceEntity getFreelance() {
        return freelance;
    }

    public void setFreelance(FreelanceEntity freelance) {
        this.freelance = freelance;
    }
}
