package com.kodoops.fenwork.freelance.presentation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kodoops.fenwork.freelance.application.service.PortfolioService;
import com.kodoops.fenwork.freelance.domain.model.Portfolio;
import com.kodoops.fenwork.freelance.presentation.request.PortfolioRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class PortfolioControllerTest {

    private MockMvc mockMvc;
    private PortfolioService portfolioService;
    private PortfolioController portfolioController;
    private Portfolio project;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        objectMapper.registerModule(new JavaTimeModule());

        portfolioService = mock(PortfolioService.class);
        portfolioController = new PortfolioController(portfolioService);
        mockMvc = MockMvcBuilders.standaloneSetup(portfolioController).build();

        project = new Portfolio("project1", "Project Title", "Description", "Client Name", LocalDate.now(), "1");
    }

    @Test
    void shouldAddProjectToPortfolio() throws Exception {
        when(portfolioService.addProjectToPortfolio(eq("1"), any(PortfolioRequest.class))).thenReturn(project);
        PortfolioRequest request = new PortfolioRequest( "Project Title", "Description", "Client Name", LocalDate.now());

        ResultActions result = mockMvc.perform(post("/api/freelances/1/portfolio")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.projectId").value("project1"))
                .andExpect(jsonPath("$.data.title").value("Project Title"));
    }

    @Test
    void shouldListPortfolioProjects() throws Exception {
        when(portfolioService.listPortfolioProjects("1")).thenReturn(List.of(project));

        mockMvc.perform(get("/api/freelances/1/portfolio"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].projectId").value("project1"));
    }
}
