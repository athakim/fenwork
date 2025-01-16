package com.kodoops.fenwork.freelance.application.service;

import com.kodoops.fenwork.freelance.domain.model.Evaluation;
import com.kodoops.fenwork.freelance.domain.model.Freelance;
import com.kodoops.fenwork.freelance.domain.model.Portfolio;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class EvaluationService {

    private final FreelanceService freelanceService;

    public EvaluationService(FreelanceService freelanceService) {
        this.freelanceService = freelanceService;
    }

    // Ajouter une évaluation à un projet d'un freelance
    public Evaluation addEvaluationToProject(String freelanceId, String projectId, Evaluation evaluation) {
        Freelance freelance = freelanceService.getFreelanceById(freelanceId);

        // Vérifier que le projet existe dans le portfolio
        Portfolio project = freelance.getPortfolio().stream()
                .filter(p -> p.getProjectId().equals(projectId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Project not found in portfolio"));

        // Ajouter l'évaluation
        freelance.addEvaluation(evaluation); // Ajoute l'évaluation au projet
        freelanceService.updateFreelance(freelanceId, freelance); // Sauvegarde du freelance mis à jour
        return evaluation;
    }
}
