package com.kodoops.fenwork.freelance.presentation.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class EvaluationRequest {

    private int rating; // 1 to 5
    private String comment;

    public EvaluationRequest() {
    }

    public EvaluationRequest(int rating, String comment) {
        this.rating = rating;
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }
}
