package com.kodoops.fenwork.freelance.presentation.mapper;

import com.kodoops.fenwork.freelance.domain.model.Portfolio;
import com.kodoops.fenwork.freelance.presentation.response.PortfolioResponse;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PortfolioResponseMapper {

    /**
     * Convertit un objet Portfolio en PortfolioResponse
     */
    public static PortfolioResponse toResponse(Portfolio portfolio) {
        return new PortfolioResponse(
                portfolio.getProjectId(),
                portfolio.getTitle(),
                portfolio.getDescription(),
                portfolio.getClientName(),
                portfolio.getCompletionDate()
        );
    }

    /**
     * Convertit une liste de Portfolio en une liste de PortfolioResponse
     */
    public static List<PortfolioResponse> toResponseList(List<Portfolio> portfolios) {
        return portfolios.stream()
                .map(PortfolioResponseMapper::toResponse)
                .collect(Collectors.toList());
    }
}