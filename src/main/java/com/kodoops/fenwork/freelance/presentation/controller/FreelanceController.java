package com.kodoops.fenwork.freelance.presentation.controller;

import com.kodoops.fenwork.freelance.application.service.FreelanceService;
import com.kodoops.fenwork.freelance.domain.vo.Availability;
import com.kodoops.fenwork.freelance.domain.model.Freelance;
import com.kodoops.fenwork.freelance.presentation.mapper.FreelanceResponseMapper;
import com.kodoops.fenwork.freelance.presentation.request.FreelanceRequest;
import com.kodoops.fenwork.freelance.presentation.response.FreelanceResponse;
import com.kodoops.fenwork.freelance.presentation.response.ResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.kodoops.fenwork.freelance.application.service.FreelanceService.getAvailability;

@RestController
@RequestMapping("/api/freelances")
public class FreelanceController {

    private final FreelanceService freelanceService;

    public FreelanceController(FreelanceService freelanceService) {
        this.freelanceService = freelanceService;
    }

    @PostMapping
    public ResponseEntity<ResponseDto> createFreelance(@Valid @RequestBody FreelanceRequest freelance) {
        String message = "Freelance crée avec succès.";

        return ResponseEntity.ok(getOkFreelanceResponseDto(freelanceService.createFreelance(freelance), message));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateFreelance(@PathVariable String id, @Valid @RequestBody FreelanceRequest request) {
        Freelance freelance = new Freelance(
                id,
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getProfilePictureUrl(),
                request.getBio(),
                request.getAvailability()!=null ?getAvailability(request.getAvailability()): Availability.UNAVAILABLE,
                request.getTenantId()
        );
        String message = "Les ifnormations du freelance ont été mises a jour.";

        return ResponseEntity.ok(getOkFreelanceResponseDto(freelanceService.updateFreelance(id, freelance),message));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getFreelanceById(@PathVariable String id) {
        String message = "Freelance trouvé.";
        return ResponseEntity.ok(getOkFreelanceResponseDto(freelanceService.getFreelanceById(id), message));
    }

    @GetMapping
    public ResponseEntity<ResponseDto> listAllFreelances() {
        String message= "La liste des freelances";

        return ResponseEntity.ok(getOkFreelancesResponsesDto(freelanceService.listFreelances(), message));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteFreelanceById(@PathVariable String id) {
        String message = "Freelance supprimé.";
        freelanceService.deleteFreelance(id);
        return ResponseEntity.ok(getOkResponse(null, message));
    }

    public static ResponseDto getOkFreelancesResponsesDto(List<Freelance> freelances, String message) {
        List<FreelanceResponse> freelancesDtos =
                freelances.stream().map(FreelanceResponseMapper::toResponse).toList();
        return getOkResponse(freelancesDtos, message);
    }

    public static ResponseDto getOkFreelanceResponseDto(Freelance freelance, String message) {
        FreelanceResponse freelanceDto = FreelanceResponseMapper.toResponse(freelance);

        return getOkResponse( freelanceDto, message);
    }

    public static ResponseDto getOkResponse(Object data, String message) {
        return new ResponseDto(
                data,
                message,
                LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME),
                HttpStatus.OK.value()
        );
    }
}
