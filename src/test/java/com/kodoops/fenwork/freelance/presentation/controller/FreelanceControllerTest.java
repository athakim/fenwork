package com.kodoops.fenwork.freelance.presentation.controller;

import com.kodoops.fenwork.freelance.application.service.FreelanceService;

import com.kodoops.fenwork.freelance.domain.vo.Availability;
import com.kodoops.fenwork.freelance.domain.model.Freelance;
import com.kodoops.fenwork.freelance.presentation.request.FreelanceRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class FreelanceControllerTest {

    private MockMvc mockMvc;
    private FreelanceService freelanceService;
    private FreelanceController freelanceController;
    private Freelance freelance;

    @BeforeEach
    void setUp() {
        freelanceService = mock(FreelanceService.class);
        freelanceController = new FreelanceController(freelanceService);
        mockMvc = MockMvcBuilders.standaloneSetup(freelanceController).build();

        freelance = new Freelance("1", "John", "Doe", "john.doe@example.com",
                "profile.jpg", "Experienced developer", Availability.AVAILABLE, "tenant123");
    }

    @Test
    void shouldCreateFreelance() throws Exception {
        when(freelanceService.createFreelance(any(FreelanceRequest.class))).thenReturn(freelance);

        mockMvc.perform(post("/api/freelances")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"John\",\"lastName\":\"Doe\",\"email\":\"john.doe@example.com\",\"tenantId\":\"tenant123\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").isString())
                .andExpect(jsonPath("$.data.firstName").value("John"));
    }

    @Test
    void shouldUpdateFreelance() throws Exception {
        when(freelanceService.updateFreelance(eq("1"), any(Freelance.class))).thenReturn(freelance);

        mockMvc.perform(put("/api/freelances/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"John\",\"lastName\":\"Doe\",\"email\":\"john.doe@example.com\",\"tenantId\":\"tenant123\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.firstName").value("John"));
    }

    @Test
    void shouldGetFreelanceById() throws Exception {
        when(freelanceService.getFreelanceById("1")).thenReturn(freelance);

        mockMvc.perform(get("/api/freelances/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value("1"))
                .andExpect(jsonPath("$.data.firstName").value("John"));
    }

    @Test
    void shouldListAllFreelances() throws Exception {
        when(freelanceService.listFreelances()).thenReturn(List.of(freelance));

        mockMvc.perform(get("/api/freelances"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.length()").value(1))
                .andExpect(jsonPath("$.data[0].id").isString());
    }
}
