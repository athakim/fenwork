package com.kodoops.fenwork.freelance.presentation.controller;

import com.kodoops.fenwork.freelance.application.service.SkillService;
import com.kodoops.fenwork.freelance.domain.model.Skill;
import com.kodoops.fenwork.freelance.presentation.mapper.SkillResponseMapper;
import com.kodoops.fenwork.freelance.presentation.request.SkillRequest;
import com.kodoops.fenwork.freelance.presentation.response.ResponseDto;
import com.kodoops.fenwork.freelance.presentation.response.SkillResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@RestController
@RequestMapping("/api/freelances/{freelanceId}/skills")
public class SkillController {

    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @PostMapping
    public ResponseEntity<ResponseDto> addSkillToFreelance(
            @PathVariable String freelanceId,
            @Valid @RequestBody SkillRequest skillRequest) {
        String message="Compétence ajoutée au Freelance";
        return ResponseEntity.ok(getOkSkillResponseDto(skillService.addSkillToFreelance(freelanceId, skillRequest),message, HttpStatus.CREATED));
    }

    @DeleteMapping("/{skillId}")
    public ResponseEntity<Void> removeSkillFromFreelance(@PathVariable String freelanceId, @PathVariable String skillId) {
        skillService.removeSkillFromFreelance(freelanceId, skillId);
        return ResponseEntity.noContent().build();
    }

    public static ResponseDto getOkSkillsResponsesDto(List<Skill> skills, String message) {

        return getOkResponse(SkillResponseMapper.toResponseList(skills), message, HttpStatus.OK);
    }

    public static ResponseDto getOkSkillResponseDto(Skill skill, String message, HttpStatus status) {
        SkillResponse skillDto = SkillResponseMapper.toResponse(skill);

        return getOkResponse( skillDto, message, status);
    }

    public static ResponseDto getOkResponse(Object data, String message, HttpStatus status) {
        if (status == null) {
            status = HttpStatus.OK;
        }
        return new ResponseDto(
                data,
                message,
                LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME),
                status.value()
        );
    }
}
