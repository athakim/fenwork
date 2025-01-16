package com.kodoops.fenwork.freelance.infrastructure.persistence;

import com.kodoops.fenwork.freelance.domain.exception.FreelanceException;
import com.kodoops.fenwork.freelance.domain.model.Portfolio;
import com.kodoops.fenwork.freelance.domain.repository.PortfolioRepository;
import com.kodoops.fenwork.freelance.infrastructure.mapper.PortfolioMapper;
import com.kodoops.fenwork.freelance.infrastructure.persistence.entity.FreelanceEntity;
import com.kodoops.fenwork.freelance.infrastructure.persistence.entity.PortfolioEntity;
import com.kodoops.fenwork.freelance.infrastructure.persistence.jpa.JpaFreelanceRepository;
import com.kodoops.fenwork.freelance.infrastructure.persistence.jpa.JpaPortfolioRepository;
import org.springframework.stereotype.Repository;

@Repository
public class PortfolioRepositoryImpl implements PortfolioRepository {

    private final JpaPortfolioRepository jpaPortfolioRepository;
    private final JpaFreelanceRepository jpaFreelanceRepository;

    public PortfolioRepositoryImpl(JpaPortfolioRepository jpaPortfolioRepository, JpaFreelanceRepository jpaFreelanceRepository) {
        this.jpaPortfolioRepository = jpaPortfolioRepository;
        this.jpaFreelanceRepository = jpaFreelanceRepository;
    }

    @Override
    public Portfolio save(Portfolio portfolio) {
        FreelanceEntity freelanceEntity = jpaFreelanceRepository.findById(portfolio.getFreelanceId())
                .orElseThrow(() -> new FreelanceException("Freelance non disponible."));

        PortfolioEntity portfolioEntity = jpaPortfolioRepository.save(PortfolioMapper.toEntity(portfolio, freelanceEntity));
        return PortfolioMapper.toDomain(portfolioEntity);
    }

    @Override
    public void deleteByProjectId(String projectId) {
        jpaPortfolioRepository.findById(projectId).ifPresent(jpaPortfolioRepository::delete);
    }
}
