package com.kodoops.fenwork.freelance.domain.repository;

import com.kodoops.fenwork.freelance.domain.model.Portfolio;

public interface PortfolioRepository {
    Portfolio save(Portfolio portfolio);

    void deleteByProjectId(String projectId);
}
