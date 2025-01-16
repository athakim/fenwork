package com.kodoops.fenwork.freelance.application.service;

import com.kodoops.fenwork.freelance.domain.exception.FreelanceException;
import com.kodoops.fenwork.freelance.domain.model.SkillCategory;
import com.kodoops.fenwork.freelance.domain.repository.SkillCategoryRepository;
import com.kodoops.fenwork.freelance.presentation.request.SkillCategoryRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class SkillCategoryService {

    private final SkillCategoryRepository skillCategoryRepository;

    public SkillCategoryService(SkillCategoryRepository repository) {
        this.skillCategoryRepository = repository;
    }

    public List<SkillCategory> getAllCategories() {
        return skillCategoryRepository.findAll();
    }

    public Optional<SkillCategory> getCategoryById(String id) {
        return skillCategoryRepository.findById(id);
    }

    public SkillCategory createCategory(SkillCategoryRequest request) {
        SkillCategory category =new SkillCategory(
                UUID.randomUUID().toString(),
                request.getName(),
                request.getDescription()
        );
        return skillCategoryRepository.save(category);
    }

    public SkillCategory updateCategory(String id, SkillCategoryRequest request) {
        SkillCategory existing = getCategoryById(id)
                .orElseThrow(()-> new FreelanceException("La catégorie des compétences n'existe pas"));
        existing = new SkillCategory(existing.getId(), request.getName(), request.getDescription());
        return skillCategoryRepository.save(existing);
    }

    public void deleteCategory(String id) {
        skillCategoryRepository.deleteById(id);
    }
}