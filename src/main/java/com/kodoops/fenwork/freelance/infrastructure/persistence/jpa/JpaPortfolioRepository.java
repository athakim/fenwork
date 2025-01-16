package com.kodoops.fenwork.freelance.infrastructure.persistence.jpa;

import com.kodoops.fenwork.freelance.infrastructure.persistence.entity.PortfolioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaPortfolioRepository extends JpaRepository<PortfolioEntity, String> {
}
