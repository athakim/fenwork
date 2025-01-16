package com.kodoops.fenwork.freelance.application.service;

import com.kodoops.fenwork.freelance.domain.vo.Availability;
import com.kodoops.fenwork.freelance.domain.model.Freelance;
import com.kodoops.fenwork.freelance.domain.model.Skill;
import com.kodoops.fenwork.freelance.domain.repository.SkillRepository;
import com.kodoops.fenwork.freelance.domain.model.SkillCategory;
import com.kodoops.fenwork.freelance.infrastructure.persistence.SkillCategoryRepositoryImpl;
import com.kodoops.fenwork.freelance.presentation.request.SkillRequest;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SkillServiceTest {

    private final FreelanceService freelanceService = mock(FreelanceService.class);
    private final SkillRepository skillRepository = mock(SkillRepository.class);
    private final SkillCategoryRepositoryImpl skillCategoryRepository= mock(SkillCategoryRepositoryImpl.class);
    private final SkillService skillService = new SkillService(freelanceService, skillRepository, skillCategoryRepository);


    @Test
    void shouldAddSkillToFreelance() {
        Freelance freelance = new Freelance("1", "John", "Doe", "john.doe@example.com",
                "profile.jpg", "Experienced developer", Availability.AVAILABLE, "tenant123");

        // Mock du service pour retourner un Freelance valide
        when(freelanceService.getFreelanceById("1")).thenReturn(freelance);
        when(freelanceService.save(any(Freelance.class))).thenReturn(freelance);

        SkillCategory category = new SkillCategory("1", "Backend", "Backend development skills");
        when(skillCategoryRepository.findById("1")).thenReturn(Optional.of(category));

        SkillRequest skillRequest = new SkillRequest("Java", category.getId(), "Expert");

        Skill expectedSkill = new Skill("1", "Java", category, "Expert", "1");
        when(skillRepository.save(any(Skill.class))).thenReturn(expectedSkill); // Mock du skill repository

        Skill result = skillService.addSkillToFreelance("1", skillRequest);

        // Capture du skill sauvegardé
        ArgumentCaptor<Skill> captor = ArgumentCaptor.forClass(Skill.class);
        verify(skillRepository, times(1)).save(captor.capture()); // 🔥 Vérifie que skillRepository.save() a bien été appelé
        Skill capturedSkill = captor.getValue();

        // Vérifications
        assertNotNull(capturedSkill);
        assertEquals("Java", capturedSkill.getName());
        assertEquals(category, capturedSkill.getCategory());
        assertEquals("Expert", capturedSkill.getLevel());

        assertNotNull(result);
        assertEquals("Java", result.getName());
        assertEquals(1, freelance.getSkills().size());
    }
    @Test
    void shouldRemoveSkillFromFreelance() {
        Freelance freelance = new Freelance("1", "John", "Doe", "john.doe@example.com",
                "profile.jpg", "Experienced developer", Availability.AVAILABLE, "tenant123");

        SkillCategory category = new SkillCategory("1", "Backend", "Backend development skills");
        Skill skill = new Skill("1", "Java", category, "Expert", "1");
        freelance.addSkill(skill);

        when(freelanceService.getFreelanceById("1")).thenReturn(freelance);
        when(skillRepository.findById("1")).thenReturn(Optional.of(skill));

        skillService.removeSkillFromFreelance("1", "1");

        assertTrue(freelance.getSkills().isEmpty());
        verify(skillRepository, times(1)).deleteById("1");
    }
}
