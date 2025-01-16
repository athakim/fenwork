package com.kodoops.fenwork.freelance.infrastructure.persistence;

import com.kodoops.fenwork.freelance.domain.exception.FreelanceException;
import com.kodoops.fenwork.freelance.domain.model.Skill;
import com.kodoops.fenwork.freelance.domain.repository.SkillRepository;
import com.kodoops.fenwork.freelance.infrastructure.mapper.SkillMapper;
import com.kodoops.fenwork.freelance.infrastructure.persistence.entity.FreelanceEntity;
import com.kodoops.fenwork.freelance.infrastructure.persistence.entity.SkillEntity;
import com.kodoops.fenwork.freelance.infrastructure.persistence.jpa.JpaFreelanceRepository;
import com.kodoops.fenwork.freelance.infrastructure.persistence.jpa.JpaSkillRepository;
import jdk.jfr.Registered;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SkillRepositoryImpl implements SkillRepository {

    private final JpaSkillRepository jpaSkillRepository;
    private final JpaFreelanceRepository jpaFreelanceRepository;

    public SkillRepositoryImpl(JpaSkillRepository jpaSkillRepository, JpaFreelanceRepository jpaFreelanceRepository) {
        this.jpaSkillRepository = jpaSkillRepository;
        this.jpaFreelanceRepository = jpaFreelanceRepository;
    }

    @Override
    public Skill save(Skill skill) {
        FreelanceEntity freelanceEntity = jpaFreelanceRepository.findById(skill.getFreelanceId())
                .orElseThrow(() -> new FreelanceException("Freelance non disponible."));
        SkillEntity skillEntity = SkillMapper.toEntity(skill, freelanceEntity);
        SkillEntity saved = jpaSkillRepository.save(skillEntity);
        return SkillMapper.toDomain(saved);
    }

    @Override
    public Optional<Skill> findById(String skillId) {
        Optional<SkillEntity> skillEntity = jpaSkillRepository.findById(skillId);

        return skillEntity.map(SkillMapper::toDomain);
    }

    @Override
    public void deleteById(String skillId) {
        jpaSkillRepository.findById(skillId).ifPresent(jpaSkillRepository::delete);
    }

    @Override
    public List<Skill> findAll() {
        List<SkillEntity> skillEntities = jpaSkillRepository.findAll();
        return skillEntities.stream().map(SkillMapper::toDomain).toList();
    }
}
