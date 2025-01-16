package com.kodoops.fenwork.freelance.infrastructure.persistence;

import com.kodoops.fenwork.freelance.domain.repository.SkillCategoryRepository;
import com.kodoops.fenwork.freelance.domain.model.SkillCategory;
import com.kodoops.fenwork.freelance.infrastructure.mapper.SkillCategoryMapper;
import com.kodoops.fenwork.freelance.infrastructure.persistence.entity.SkillCategoryEntity;
import com.kodoops.fenwork.freelance.infrastructure.persistence.jpa.JpaSkillCategoryRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SkillCategoryRepositoryImpl implements SkillCategoryRepository {

    private final JpaSkillCategoryRepository jpaSkillCategoryRepository;

    public SkillCategoryRepositoryImpl(JpaSkillCategoryRepository jpaSkillCategoryRepository) {
        this.jpaSkillCategoryRepository = jpaSkillCategoryRepository;
    }


    @Override
    public Optional<SkillCategory> findById(String categoryId) {
        Optional<SkillCategoryEntity> byId = jpaSkillCategoryRepository.findById(categoryId);

        return byId.map(SkillCategoryMapper::toDomain);
    }

    @Override
    public List<SkillCategory> findAll() {
        List<SkillCategoryEntity> all = jpaSkillCategoryRepository.findAll();

        return all.stream().map(SkillCategoryMapper::toDomain).toList();
    }

    @Override
    public SkillCategory save(SkillCategory category) {
        SkillCategoryEntity saved = jpaSkillCategoryRepository.save(SkillCategoryMapper.toEntity(category));
        return SkillCategoryMapper.toDomain(saved);
    }

    @Override
    public void deleteById(String id) {
        jpaSkillCategoryRepository.findById(id).ifPresent(jpaSkillCategoryRepository::delete);
    }
}
