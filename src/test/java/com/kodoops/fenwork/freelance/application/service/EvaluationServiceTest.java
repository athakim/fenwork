package com.kodoops.fenwork.freelance.application.service;

import com.kodoops.fenwork.freelance.domain.vo.Availability;
import com.kodoops.fenwork.freelance.domain.model.Evaluation;
import com.kodoops.fenwork.freelance.domain.model.Freelance;
import com.kodoops.fenwork.freelance.domain.model.Portfolio;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EvaluationServiceTest {

    private final FreelanceService freelanceService = mock(FreelanceService.class);
    private final EvaluationService evaluationService = new EvaluationService(freelanceService);

    @Test
    void shouldAddEvaluationToProject() {
        Portfolio project = new Portfolio("project1", "Project Title", "Description",
                "Client Name", java.time.LocalDate.now(), "1");

        Freelance freelance = new Freelance("1", "John", "Doe", "john.doe@example.com",
                "profile.jpg", "Experienced developer", Availability.AVAILABLE, "tenant123");
        freelance.addProject(project);

        when(freelanceService.getFreelanceById("1")).thenReturn(freelance);

        Evaluation evaluation = new Evaluation("evaluation1", 5, "Great work",
                LocalDateTime.now(), "project1");

        Evaluation result = evaluationService.addEvaluationToProject("1", "project1", evaluation);

        assertNotNull(result);
        assertEquals(1, freelance.getEvaluations().size());
        verify(freelanceService, times(1)).updateFreelance("1", freelance);
    }

    @Test
    void shouldThrowExceptionIfProjectNotFound() {
        Freelance freelance = new Freelance("1", "John", "Doe", "john.doe@example.com",
                "profile.jpg", "Experienced developer", Availability.AVAILABLE, "tenant123");

        when(freelanceService.getFreelanceById("1")).thenReturn(freelance);

        Evaluation evaluation = new Evaluation("evaluation1", 5, "Great work",
                LocalDateTime.now(), "nonexistentProject");

        assertThrows(IllegalArgumentException.class, () ->
                evaluationService.addEvaluationToProject("1", "nonexistentProject", evaluation));
    }
}
