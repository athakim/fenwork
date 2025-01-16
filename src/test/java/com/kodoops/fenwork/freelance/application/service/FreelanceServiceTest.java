package com.kodoops.fenwork.freelance.application.service;

import com.kodoops.fenwork.freelance.domain.vo.Availability;
import com.kodoops.fenwork.freelance.domain.model.Freelance;
import com.kodoops.fenwork.freelance.domain.repository.FreelanceRepository;
import com.kodoops.fenwork.freelance.presentation.request.FreelanceRequest;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FreelanceServiceTest {

    private final FreelanceRepository freelanceRepository = mock(FreelanceRepository.class);
    private final FreelanceService freelanceService = new FreelanceService(freelanceRepository);


    @Test
    void shouldCreateFreelanceSuccessfully() {
        FreelanceRequest request = new FreelanceRequest(
                "John",
                "Doe",
                "john.doe@example.com",
                "profile.jpg",
                "Experienced developer",
                "available",
                "tenant123"
        );

        Freelance expectedFreelance = new Freelance("1", "John", "Doe", "john.doe@example.com",
                "profile.jpg", "Experienced developer", Availability.AVAILABLE, "tenant123");

        when(freelanceRepository.save(argThat(freelance ->
                freelance.getFirstName().equals("John") &&
                        freelance.getLastName().equals("Doe") &&
                        freelance.getEmail().equals("john.doe@example.com")
        ))).thenReturn(expectedFreelance);

        Freelance createdFreelance = freelanceService.createFreelance(request);

        assertNotNull(createdFreelance);
        assertEquals("John", createdFreelance.getFirstName());
        verify(freelanceRepository, times(1)).save(argThat(freelance ->
                freelance.getFirstName().equals("John") &&
                        freelance.getLastName().equals("Doe") &&
                        freelance.getEmail().equals("john.doe@example.com")
        ));
    }

    @Test
    void shouldUpdateFreelanceSuccessfully() {
        Freelance existingFreelance = new Freelance("1", "John", "Doe", "john.doe@example.com",
                "profile.jpg", "Experienced developer", Availability.AVAILABLE, "tenant123");

        when(freelanceRepository.findById("1")).thenReturn(Optional.of(existingFreelance));
        when(freelanceRepository.save(any(Freelance.class))).thenReturn(existingFreelance);

        Freelance updatedFreelance = new Freelance("1", "Jane", "Smith", "jane.smith@example.com",
                "profile_new.jpg", "Updated bio", Availability.BUSY, "tenant123");

        Freelance result = freelanceService.updateFreelance("1", updatedFreelance);

        assertEquals("Jane", result.getFirstName());
        assertEquals("Smith", result.getLastName());
        verify(freelanceRepository, times(1)).save(existingFreelance);
    }

    @Test
    void shouldThrowExceptionIfFreelanceNotFound() {
        when(freelanceRepository.findById("99")).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> freelanceService.getFreelanceById("99"));
    }
}
