package com.kodoops.fenwork.freelance.application.config;

import com.kodoops.fenwork.freelance.application.service.*;
import com.kodoops.fenwork.freelance.domain.repository.FreelanceRepository;
import com.kodoops.fenwork.freelance.domain.repository.PortfolioRepository;
import com.kodoops.fenwork.freelance.domain.repository.SkillCategoryRepository;
import com.kodoops.fenwork.freelance.domain.repository.SkillRepository;
import com.kodoops.fenwork.freelance.infrastructure.persistence.FreelanceRepositoryImpl;
import com.kodoops.fenwork.freelance.infrastructure.persistence.SkillRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServicesConfig {

    private final FreelanceRepository freelanceRepository;
    private final SkillRepository skillRepository;
    private final SkillCategoryRepository skillCategoryRepository;
    private final PortfolioRepository portfolioRepository;

    public ServicesConfig(FreelanceRepository freelanceRepository, SkillRepository skillRepository, SkillCategoryRepository skillCategoryRepository, PortfolioRepository portfolioRepository) {
        this.freelanceRepository = freelanceRepository;
        this.skillRepository = skillRepository;
        this.skillCategoryRepository = skillCategoryRepository;
        this.portfolioRepository = portfolioRepository;
    }

    @Bean
    public FreelanceService freelanceService(){
        return  new FreelanceService(freelanceRepository);
    }


    @Bean
    public SkillService skillService(){
        return new SkillService(freelanceService(), skillRepository, skillCategoryRepository);
    }

    @Bean
    public PortfolioService portfolioService(){
        return new PortfolioService(freelanceService(), portfolioRepository);
    }

    @Bean
    public EvaluationService evaluationService(){
        return new EvaluationService(freelanceService());
    }

    @Bean
    public SkillCategoryService skillCategoryService(){
        return new SkillCategoryService(skillCategoryRepository);
    }
}
