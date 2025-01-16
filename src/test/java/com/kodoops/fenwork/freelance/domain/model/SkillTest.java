package com.kodoops.fenwork.freelance.domain.model;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SkillTest {

    @Test
    void shouldCreateSkillSuccessfully() {
        SkillCategory category = new SkillCategory("1", "Backend", "Skills related to backend development");
        Skill skill = new Skill("1", "Java", category, "Expert", "freelance123");

        assertEquals("1", skill.getId());
        assertEquals("Java", skill.getName());
        assertEquals(category, skill.getCategory());
        assertEquals("Expert", skill.getLevel());
        assertEquals("freelance123", skill.getFreelanceId());
    }

    @Test
    void shouldThrowExceptionForInvalidSkillName() {
        SkillCategory category = new SkillCategory("1", "Backend", "Skills related to backend development");

        assertThrows(IllegalArgumentException.class, () -> new Skill("1", "", category, "Expert", "freelance123"));
        assertThrows(IllegalArgumentException.class, () -> new Skill("1", null, category, "Expert", "freelance123"));
    }
}
