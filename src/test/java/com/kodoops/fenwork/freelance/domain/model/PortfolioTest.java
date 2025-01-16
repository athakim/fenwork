package com.kodoops.fenwork.freelance.domain.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PortfolioTest {

    @Test
    void shouldCreatePortfolioSuccessfully() {
        Portfolio portfolio = new Portfolio("project1", "Project Title", "Project Description",
                "Client Name", LocalDate.now(), "1");

        assertEquals("project1", portfolio.getProjectId());
        assertEquals("Project Title", portfolio.getTitle());
        assertEquals("Project Description", portfolio.getDescription());
        assertEquals("Client Name", portfolio.getClientName());
        assertEquals(LocalDate.now(), portfolio.getCompletionDate());
    }
}
