package com.kodoops.fenwork.freelance.presentation.controller;

import com.kodoops.fenwork.freelance.application.service.EvaluationService;
import com.kodoops.fenwork.freelance.domain.model.Evaluation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class EvaluationControllerTest {

    private MockMvc mockMvc;
    private EvaluationService evaluationService;
    private EvaluationController evaluationController;
    private Evaluation evaluation;

    @BeforeEach
    void setUp() {
        evaluationService = mock(EvaluationService.class);
        evaluationController = new EvaluationController(evaluationService);
        mockMvc = MockMvcBuilders.standaloneSetup(evaluationController).build();

        evaluation = new Evaluation("evaluation1", 5, "Excellent work", LocalDateTime.now(), "project1");
    }

    @Test
    void shouldAddEvaluationToProject() throws Exception {
        when(evaluationService.addEvaluationToProject(eq("1"), eq("project1"), any(Evaluation.class))).thenReturn(evaluation);

        mockMvc.perform(post("/api/freelances/1/projects/project1/evaluations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"evaluationId\":\"evaluation1\",\"rating\":5,\"comment\":\"Excellent work\",\"projectId\":\"project1\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.evaluationId").value("evaluation1"))
                .andExpect(jsonPath("$.rating").value(5));
    }
}
