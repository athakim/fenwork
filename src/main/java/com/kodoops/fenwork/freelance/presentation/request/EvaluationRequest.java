package com.kodoops.fenwork.freelance.presentation.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationRequest {

    private int rating; // 1 to 5
    private String comment;

}
