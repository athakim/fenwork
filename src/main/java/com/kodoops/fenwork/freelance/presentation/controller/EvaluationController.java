package com.kodoops.fenwork.freelance.presentation.controller;

import com.kodoops.fenwork.freelance.application.service.EvaluationService;
import com.kodoops.fenwork.freelance.domain.model.Evaluation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/freelances/{freelanceId}/projects/{projectId}/evaluations")
public class EvaluationController {

    private final EvaluationService evaluationService;

    public EvaluationController(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @PostMapping
    public ResponseEntity<Evaluation> addEvaluationToProject(
            @PathVariable String freelanceId,
            @PathVariable String projectId,
            @Valid @RequestBody Evaluation evaluation) {
        return ResponseEntity.ok(evaluationService.addEvaluationToProject(freelanceId, projectId, evaluation));
    }
}
