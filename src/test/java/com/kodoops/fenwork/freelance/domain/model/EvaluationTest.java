package com.kodoops.fenwork.freelance.domain.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class EvaluationTest {

    @Test
    void shouldCreateEvaluationSuccessfully() {
        LocalDateTime now = LocalDateTime.now();
        Evaluation evaluation = new Evaluation("evaluation1", 5, "Excellent work", now, "project1");

        assertEquals("evaluation1", evaluation.getEvaluationId());
        assertEquals(5, evaluation.getRating());
        assertEquals("Excellent work", evaluation.getComment());
        assertEquals(now, evaluation.getCreatedAt());
        assertEquals("project1", evaluation.getProjectId());
    }
}
