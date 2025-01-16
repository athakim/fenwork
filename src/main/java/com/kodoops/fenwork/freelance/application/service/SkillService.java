package com.kodoops.fenwork.freelance.application.service;

import com.kodoops.fenwork.freelance.domain.exception.FreelanceException;
import com.kodoops.fenwork.freelance.domain.model.Freelance;
import com.kodoops.fenwork.freelance.domain.model.Skill;
import com.kodoops.fenwork.freelance.domain.repository.SkillCategoryRepository;
import com.kodoops.fenwork.freelance.domain.repository.SkillRepository;
import com.kodoops.fenwork.freelance.domain.model.SkillCategory;
import com.kodoops.fenwork.freelance.presentation.request.SkillRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Transactional
public class SkillService {

    private final FreelanceService freelanceService;
    private final SkillRepository skillRepository;
    private final SkillCategoryRepository skillCategoryRepository;

    public SkillService(FreelanceService freelanceService, SkillRepository skillRepository, SkillCategoryRepository skillCategoryRepository) {
        this.freelanceService = freelanceService;
        this.skillRepository = skillRepository;
        this.skillCategoryRepository = skillCategoryRepository;
    }

    public Skill addSkillToFreelance(String freelanceId, SkillRequest skillRequest) {
        Freelance freelance = freelanceService.getFreelanceById(freelanceId);
        SkillCategory skillCategory = skillCategoryRepository.findById(skillRequest.getCategoryId())
                .orElseThrow(()-> new FreelanceException("Catégorie de la compétence ne peut pas être nulle"));

        Skill skill = new Skill(
                UUID.randomUUID().toString(),
                skillRequest.getName(),
                skillCategory,
                skillRequest.getLevel(),
                freelanceId);
        skillRepository.save(skill);
        freelance.addSkill(skill);
        Freelance savedFreelance = freelanceService.save(freelance);
        return savedFreelance.getSkills().stream()
                .filter(s -> s.getName().equals(skill.getName()))
                .findFirst()
                .orElseThrow(() -> new FreelanceException("Erreur lors de l'ajout de la compétence"));

    }

    public void removeSkillFromFreelance(String freelanceId, String skillId) {
        Freelance freelance = freelanceService.getFreelanceById(freelanceId);
        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new FreelanceException("Compétence non trouvée."));
        freelance.removeSkill(skill);
        Freelance savedFreelance = freelanceService.save(freelance);
        skillRepository.deleteById(skillId);
    }
}
