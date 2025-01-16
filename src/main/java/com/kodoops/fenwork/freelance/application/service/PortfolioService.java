package com.kodoops.fenwork.freelance.application.service;

import com.kodoops.fenwork.freelance.domain.exception.FreelanceException;
import com.kodoops.fenwork.freelance.domain.model.Freelance;
import com.kodoops.fenwork.freelance.domain.model.Portfolio;
import com.kodoops.fenwork.freelance.domain.repository.PortfolioRepository;
import com.kodoops.fenwork.freelance.presentation.request.PortfolioRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
public class PortfolioService {

    private final FreelanceService freelanceService;
    private final PortfolioRepository portfolioRepository;

    public PortfolioService(FreelanceService freelanceService, PortfolioRepository portfolioRepository) {
        this.freelanceService = freelanceService;
        this.portfolioRepository = portfolioRepository;
    }

    public Portfolio addProjectToPortfolio(String freelanceId, PortfolioRequest request) {
        Freelance freelance = freelanceService.getFreelanceById(freelanceId);
        Portfolio portfolio = new Portfolio(
            request.getTitle(),
                request.getDescription(),
                request.getClientName(),
                request.getCompletionDate(),
                freelanceId
        );
        portfolioRepository.save(portfolio);
        freelance.addProject(portfolio);
        Freelance savedFreelance = freelanceService.save(freelance); // Mise à jour du portfolio
        return savedFreelance.getPortfolio().stream()
                .filter(s -> s.getProjectId().equals(portfolio.getProjectId()))
                .findFirst()
                .orElseThrow(() -> new FreelanceException("Erreur lors de l'ajout de la compétence"));
    }

    public List<Portfolio> listPortfolioProjects(String freelanceId) {
        Freelance freelance = freelanceService.getFreelanceById(freelanceId);
        return freelance.getPortfolio();
    }

    public Portfolio updateProjectToPortfolio(String freelanceId, String projectId, PortfolioRequest request) {
        Freelance freelance = freelanceService.getFreelanceById(freelanceId);
        Optional<Portfolio> portfolio = freelance.getPortfolio().stream().filter(
                project -> project.getProjectId().equals(projectId)
        ).findFirst();
        if(portfolio.isEmpty())
            throw new FreelanceException("Projet demandé est introuvable dans le portfolio.");
        Portfolio updatedPortfolio = portfolio.get();
        updatedPortfolio.setProjectId(projectId);
        updatedPortfolio.setTitle(request.getTitle());
        updatedPortfolio.setDescription(request.getDescription());
        updatedPortfolio.setCompletionDate(request.getCompletionDate());
        updatedPortfolio.setClientName(request.getClientName());
        Portfolio saved = portfolioRepository.save(updatedPortfolio);
        List<Portfolio> updatedPortfolios = freelance.getPortfolio().stream()
                .map(project -> project.getProjectId().equals(projectId) ? updatedPortfolio : project) // ✅ Met à jour le bon projet
                .collect(Collectors.toList());

        freelance.setPortfolio(updatedPortfolios);

        freelanceService.save(freelance);
        return saved;
    }

    public Portfolio getProjectToPortfolio(String freelanceId, String projectId) {
        Freelance freelance = freelanceService.getFreelanceById(freelanceId);
        Optional<Portfolio> portfolio = freelance.getPortfolio().stream().filter(
                project -> project.getProjectId().equals(projectId)
        ).findFirst();

        return portfolio.orElseThrow(()-> new FreelanceException("Projet non trouvé dans le portfolio."));
    }

    public void deleteProjectFromPortfolio(String freelanceId, String projectId) {
        Freelance freelance = freelanceService.getFreelanceById(freelanceId);
        Optional<Portfolio> portfolio = freelance.getPortfolio().stream().filter(
                project -> project.getProjectId().equals(projectId)
        ).findFirst();

        if (portfolio.isEmpty())
            throw new FreelanceException("Projet non trouvé dans le portfolio.");
        freelance.getPortfolio().remove(portfolio.get());
        freelanceService.save(freelance);
        portfolioRepository.deleteByProjectId(portfolio.get().getProjectId());
    }
}
