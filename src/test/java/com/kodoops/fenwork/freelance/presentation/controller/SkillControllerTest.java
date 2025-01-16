package com.kodoops.fenwork.freelance.presentation.controller;

import com.kodoops.fenwork.freelance.application.service.SkillService;
import com.kodoops.fenwork.freelance.domain.model.Skill;
import com.kodoops.fenwork.freelance.domain.model.SkillCategory;
import com.kodoops.fenwork.freelance.presentation.request.SkillRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class SkillControllerTest {

    private MockMvc mockMvc;
    private SkillService skillService;
    private SkillController skillController;
    private Skill skill;

    @BeforeEach
    void setUp() {
        skillService = mock(SkillService.class);
        skillController = new SkillController(skillService);
        mockMvc = MockMvcBuilders.standaloneSetup(skillController).build();

        SkillCategory category = new SkillCategory("1", "Backend", "Backend development");
        skill = new Skill("1", "Java", category, "Expert", "1");
    }

    @Test
    void shouldAddSkillToFreelance() throws Exception {
        when(skillService.addSkillToFreelance(eq("1"), any(SkillRequest.class))).thenReturn(skill);

        mockMvc.perform(post("/api/freelances/1/skills")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Java\",\"categoryId\":\"1\",\"level\":\"Expert\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").isString())
                .andExpect(jsonPath("$.data.name").value("Java"));
    }

    @Test
    void shouldRemoveSkillFromFreelance() throws Exception {
        mockMvc.perform(delete("/api/freelances/1/skills/1"))
                .andExpect(status().isNoContent());
    }
}
