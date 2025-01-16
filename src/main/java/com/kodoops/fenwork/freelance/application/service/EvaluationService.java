package com.kodoops.fenwork.freelance.application.service;

import com.kodoops.fenwork.freelance.domain.exception.FreelanceException;
import com.kodoops.fenwork.freelance.domain.model.Evaluation;
import com.kodoops.fenwork.freelance.domain.model.Freelance;
import com.kodoops.fenwork.freelance.domain.model.Portfolio;
import com.kodoops.fenwork.freelance.domain.repository.EvaluationRepository;
import com.kodoops.fenwork.freelance.presentation.request.EvaluationRequest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Transactional
public class EvaluationService {

    private final FreelanceService freelanceService;
    private final EvaluationRepository evaluationRepository;

    public EvaluationService(FreelanceService freelanceService, EvaluationRepository evaluationRepository) {
        this.freelanceService = freelanceService;
        this.evaluationRepository = evaluationRepository;
    }

    public Evaluation addEvaluationToProject(String freelanceId, String projectId, EvaluationRequest evaluationRequest) {
        Freelance freelance = freelanceService.getFreelanceById(freelanceId);

        Portfolio project = freelance.getPortfolio().stream()
                .filter(p -> p.getProjectId().equals(projectId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Projet introuvable dans le portfolio."));

        Evaluation evaluation = new Evaluation(
                UUID.randomUUID().toString(),
                evaluationRequest.getRating(),
                evaluationRequest.getComment(),
                LocalDateTime.now(),
                projectId,
                freelanceId
        );
        Evaluation savedEvaluation = evaluationRepository.save(evaluation);
        freelance.addEvaluation(evaluation);
        Freelance savedFreelance = freelanceService.save(freelance);

        return savedFreelance.getEvaluations().stream()
                .filter(s -> s.getProjectId().equals(evaluation.getProjectId()))
                .findFirst()
                .orElseThrow(() -> new FreelanceException("Erreur lors de l'ajout de l'évaluation"));
    }
}
