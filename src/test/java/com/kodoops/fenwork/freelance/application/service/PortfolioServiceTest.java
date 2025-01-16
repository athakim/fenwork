package com.kodoops.fenwork.freelance.application.service;

import com.kodoops.fenwork.freelance.domain.repository.PortfolioRepository;
import com.kodoops.fenwork.freelance.domain.vo.Availability;
import com.kodoops.fenwork.freelance.domain.model.Freelance;
import com.kodoops.fenwork.freelance.domain.model.Portfolio;
import com.kodoops.fenwork.freelance.presentation.request.PortfolioRequest;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PortfolioServiceTest {

    private final FreelanceService freelanceService = mock(FreelanceService.class);
    private final PortfolioRepository portfolioRepository = mock(PortfolioRepository.class);
    private final PortfolioService portfolioService = new PortfolioService(freelanceService, portfolioRepository);

    @Test
    void shouldAddProjectToPortfolio() {
        Freelance freelance = new Freelance("1", "John", "Doe", "john.doe@example.com",
                "profile.jpg", "Experienced developer", Availability.AVAILABLE, "tenant123");
        when(freelanceService.getFreelanceById("1")).thenReturn(freelance);
        Portfolio project = new Portfolio( "Project Title", "Description",
                "Client Name", LocalDate.now(), "1");
        when(portfolioRepository.save(any(Portfolio.class))).thenReturn(project);
        when(freelanceService.save(any(Freelance.class))).thenReturn(freelance);
        PortfolioRequest request = new PortfolioRequest( "Project Title", "Description",
                "Client Name", LocalDate.now());

        Portfolio result = portfolioService.addProjectToPortfolio("1", request);

        assertNotNull(result);
        assertEquals(1, freelance.getPortfolio().size());
        verify(freelanceService, times(1)).save( freelance);
    }
}
