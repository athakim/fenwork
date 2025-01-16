package com.kodoops.fenwork.freelance.application.config;

import com.kodoops.fenwork.freelance.application.service.*;
import com.kodoops.fenwork.freelance.domain.repository.*;
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
    private final EvaluationRepository evaluationRepository;

    public ServicesConfig(FreelanceRepository freelanceRepository, SkillRepository skillRepository, SkillCategoryRepository skillCategoryRepository, PortfolioRepository portfolioRepository, EvaluationRepository evaluationRepository) {
        this.freelanceRepository = freelanceRepository;
        this.skillRepository = skillRepository;
        this.skillCategoryRepository = skillCategoryRepository;
        this.portfolioRepository = portfolioRepository;
        this.evaluationRepository = evaluationRepository;
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
        return new EvaluationService(freelanceService(), evaluationRepository);
    }

    @Bean
    public SkillCategoryService skillCategoryService(){
        return new SkillCategoryService(skillCategoryRepository);
    }
}
