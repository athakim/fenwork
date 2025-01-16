package com.kodoops.fenwork.freelance.application.service;

import com.kodoops.fenwork.freelance.domain.vo.Availability;
import com.kodoops.fenwork.freelance.domain.model.Freelance;
import com.kodoops.fenwork.freelance.domain.model.Portfolio;
import com.kodoops.fenwork.freelance.domain.repository.FreelanceRepository;
import com.kodoops.fenwork.freelance.presentation.request.FreelanceRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Transactional
public class FreelanceService {

    private final FreelanceRepository freelanceRepository;

    public FreelanceService(FreelanceRepository freelanceRepository) {
        this.freelanceRepository = freelanceRepository;
    }

    public Freelance createFreelance(FreelanceRequest request) {

        Freelance freelance = new Freelance(
                UUID.randomUUID().toString(),
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getProfilePictureUrl(),
                request.getBio(),
                request.getAvailability()!=null ?getAvailability(request.getAvailability()):Availability.UNAVAILABLE,
                request.getTenantId()
        );
        return freelanceRepository.save(freelance);
    }

    public Freelance updateFreelance(String id, Freelance updatedFreelance) {
        Freelance existingFreelance = getFreelanceById(id);
        existingFreelance.updateInfo(
                updatedFreelance.getFirstName(),
                updatedFreelance.getLastName(),
                updatedFreelance.getEmail(),
                updatedFreelance.getProfilePictureUrl(),
                updatedFreelance.getBio()
        );

        return freelanceRepository.save(existingFreelance);
    }

    public Freelance addProjectToPortfolio(String freelanceId, Portfolio project) {
        Freelance freelance = getFreelanceById(freelanceId);
        freelance.addProject(project);
        return freelanceRepository.save(freelance);
    }

    public Freelance getFreelanceById(String id) {
        return freelanceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Freelance non trouvé."));
    }

    public List<Freelance> listFreelances() {
        return freelanceRepository.findAll();
    }

    public void deleteFreelance(String id) {
         freelanceRepository.deleteFreelance(id);
    }

    public static Availability getAvailability(String requestedAvailability) {
        Availability availability ;
        switch (requestedAvailability){
            case "available" :
                availability = Availability.AVAILABLE;
                break;
            case "unavailable" :
                availability = Availability.UNAVAILABLE;
                break;
            default:
                availability = Availability.AVAILABLE;
                break;
        }
        return availability;
    }

    public Freelance save(Freelance freelance) {
        return freelanceRepository.save(freelance);
    }
}
