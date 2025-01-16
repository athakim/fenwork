package com.kodoops.fenwork.freelance.domain.model;

import com.kodoops.fenwork.freelance.domain.vo.Availability;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class FreelanceTest {

    @Test
    void shouldCreateFreelanceSuccessfully() {
        Freelance freelance = new Freelance("1", "John", "Doe", "john.doe@example.com",
                "profile.jpg", "Experienced developer", Availability.AVAILABLE, "tenant123");

        assertEquals("1", freelance.getId());
        assertEquals("John", freelance.getFirstName());
        assertEquals("Doe", freelance.getLastName());
        assertEquals("john.doe@example.com", freelance.getEmail());
        assertEquals("profile.jpg", freelance.getProfilePictureUrl());
        assertEquals("Experienced developer", freelance.getBio());
        assertEquals(Availability.AVAILABLE, freelance.getAvailability());
        assertEquals("tenant123", freelance.getTenantId());
        assertTrue(freelance.getSkills().isEmpty());
        assertTrue(freelance.getPortfolio().isEmpty());
    }

    @Test
    void shouldAddAndRemoveSkills() {
        Freelance freelance = new Freelance("1", "John", "Doe", "john.doe@example.com",
                "profile.jpg", "Experienced developer", Availability.AVAILABLE, "tenant123");

        SkillCategory category = new SkillCategory("1", "Backend", "Skills related to backend development");
        Skill skill = new Skill("1", "Java", category, "Expert", "freelance123");

        freelance.addSkill(skill);
        assertEquals(1, freelance.getSkills().size());
        assertEquals(skill, freelance.getSkills().get(0));

        freelance.removeSkill(skill);
        assertTrue(freelance.getSkills().isEmpty());
    }

    @Test
    void shouldAddProjectToPortfolio() {
        Freelance freelance = new Freelance("1", "John", "Doe", "john.doe@example.com",
                "profile.jpg", "Experienced developer", Availability.AVAILABLE, "tenant123");

        Portfolio project = new Portfolio("project1", "Project Title", "Project Description",
                "Client Name", java.time.LocalDate.now(), "1");
        freelance.addProject(project);

        assertEquals(1, freelance.getPortfolio().size());
        assertEquals(project, freelance.getPortfolio().get(0));
    }
}
