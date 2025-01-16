package com.kodoops.fenwork.freelance.domain.vo;


import com.kodoops.fenwork.freelance.domain.model.SkillCategory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SkillCategoryTest {

    @Test
    void shouldCreateSkillCategorySuccessfully() {
        SkillCategory category = new SkillCategory("1", "Backend", "Skills related to backend development");

        assertEquals("1", category.getId());
        assertEquals("Backend", category.getName());
        assertEquals("Skills related to backend development", category.getDescription());
    }

    @Test
    void shouldThrowExceptionForInvalidCategory() {
        assertThrows(IllegalArgumentException.class, () -> new SkillCategory("", "Backend", "Description"));
        assertThrows(IllegalArgumentException.class, () -> new SkillCategory("1", "", "Description"));
        assertThrows(IllegalArgumentException.class, () -> new SkillCategory("1", "Backend", ""));
    }

    @Test
    void shouldReturnFullDescription() {
        SkillCategory category = new SkillCategory("1", "Backend", "Skills related to backend development");
        assertEquals("Backend: Skills related to backend development", category.fullDescription());
    }
}
