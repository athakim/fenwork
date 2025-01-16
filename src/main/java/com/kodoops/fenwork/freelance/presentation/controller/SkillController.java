package com.kodoops.fenwork.freelance.presentation.controller;

import com.kodoops.fenwork.freelance.application.service.SkillService;
import com.kodoops.fenwork.freelance.domain.model.Skill;
import com.kodoops.fenwork.freelance.presentation.request.SkillRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/freelances/{freelanceId}/skills")
public class SkillController {

    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @PostMapping
    public ResponseEntity<Skill> addSkillToFreelance(
            @PathVariable String freelanceId,
            @Valid @RequestBody SkillRequest skillRequest) {
        return ResponseEntity.ok(skillService.addSkillToFreelance(freelanceId, skillRequest));
    }

    @DeleteMapping("/{skillId}")
    public ResponseEntity<Void> removeSkillFromFreelance(@PathVariable String freelanceId, @PathVariable String skillId) {
        skillService.removeSkillFromFreelance(freelanceId, skillId);
        return ResponseEntity.noContent().build();
    }
}
