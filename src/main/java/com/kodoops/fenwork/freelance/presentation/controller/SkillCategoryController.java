package com.kodoops.fenwork.freelance.presentation.controller;

import com.kodoops.fenwork.freelance.application.service.SkillCategoryService;
import com.kodoops.fenwork.freelance.domain.exception.FreelanceException;
import com.kodoops.fenwork.freelance.domain.model.SkillCategory;
import com.kodoops.fenwork.freelance.infrastructure.persistence.entity.SkillCategoryEntity;
import com.kodoops.fenwork.freelance.presentation.mapper.PortfolioResponseMapper;
import com.kodoops.fenwork.freelance.presentation.mapper.SkillCategoryResponseMapper;
import com.kodoops.fenwork.freelance.presentation.request.SkillCategoryRequest;
import com.kodoops.fenwork.freelance.presentation.response.ResponseDto;
import com.kodoops.fenwork.freelance.presentation.response.SkillCategoryResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/skills/categories")
public class SkillCategoryController {

    private final SkillCategoryService skillCategoryService;

    public SkillCategoryController(SkillCategoryService skillCategoryService) {
        this.skillCategoryService = skillCategoryService;
    }

    @GetMapping
    public ResponseEntity<ResponseDto> getAllCategories() {
        List<SkillCategory> categories = skillCategoryService.getAllCategories();

        String message = "La liste des catégories des compétences" ;

        return ResponseEntity.ok(getOkSkillCategoriesResponsesDto(categories, message));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getCategoryById(@PathVariable String id) {
        SkillCategory category = skillCategoryService.getCategoryById(id)
                .orElseThrow(()-> new FreelanceException("Catégorie des compétences non trouvée."));
        String message = "La catégorie demandée " ;
        return ResponseEntity.ok(getOkSkillCategoryResponseDto(category, message, HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<ResponseDto> createCategory(@RequestBody SkillCategoryRequest request) {
        SkillCategory category = skillCategoryService.createCategory(request);
        String message = "La catégorie a été crée avec succès " ;
        return ResponseEntity.ok(getOkSkillCategoryResponseDto(category, message, HttpStatus.CREATED));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateCategory(
            @PathVariable String id,
            @RequestBody SkillCategoryRequest request) {
        SkillCategory updatedCategory = skillCategoryService.updateCategory(id, request);
        String message = "La catégorie a été mise à jour avec succès " ;
        return ResponseEntity.ok(getOkSkillCategoryResponseDto(updatedCategory, message,HttpStatus.OK));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteCategory(@PathVariable String id) {
        skillCategoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    public static ResponseDto getOkSkillCategoriesResponsesDto(List<SkillCategory> skills, String message) {

        return getOkResponse(SkillCategoryResponseMapper.toResponseList(skills), message, HttpStatus.OK);
    }

    public static ResponseDto getOkSkillCategoryResponseDto(SkillCategory skillCategory, String message, HttpStatus status) {
        SkillCategoryResponse skillCategoryDto = SkillCategoryResponseMapper.toResponse(skillCategory);

        return getOkResponse( skillCategoryDto, message, status );
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