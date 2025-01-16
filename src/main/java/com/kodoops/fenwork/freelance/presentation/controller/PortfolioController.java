package com.kodoops.fenwork.freelance.presentation.controller;

import com.kodoops.fenwork.freelance.application.service.PortfolioService;
import com.kodoops.fenwork.freelance.domain.model.Portfolio;
import com.kodoops.fenwork.freelance.domain.model.SkillCategory;
import com.kodoops.fenwork.freelance.presentation.mapper.PortfolioResponseMapper;
import com.kodoops.fenwork.freelance.presentation.mapper.SkillCategoryResponseMapper;
import com.kodoops.fenwork.freelance.presentation.request.PortfolioRequest;
import com.kodoops.fenwork.freelance.presentation.response.PortfolioResponse;
import com.kodoops.fenwork.freelance.presentation.response.ResponseDto;
import com.kodoops.fenwork.freelance.presentation.response.SkillCategoryResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/freelances/{freelanceId}/portfolio")
public class PortfolioController {

    private final PortfolioService portfolioService;

    public PortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @PostMapping
    public ResponseEntity<ResponseDto> addProjectToPortfolio(
            @PathVariable String freelanceId,
            @Valid @RequestBody PortfolioRequest portfolioRequest) {
        String message = "Projet ajouté avec succès au portfolio.";

        return ResponseEntity.ok(getOkPortfolioResponseDto(
                portfolioService.addProjectToPortfolio(freelanceId, portfolioRequest),
                message, HttpStatus.CREATED));
    }

    @PutMapping("/{projectId}")
    public ResponseEntity<ResponseDto> updateProjectToPortfolio(
            @PathVariable String freelanceId,
            @PathVariable String projectId,
            @Valid @RequestBody PortfolioRequest portfolioRequest
            ) {
        String message = "Projet mis à jour avec succès .";

        return ResponseEntity.ok(getOkPortfolioResponseDto(
                portfolioService.updateProjectToPortfolio(freelanceId, projectId, portfolioRequest),
                message, HttpStatus.OK));
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<ResponseDto> getProjectToPortfolio(
            @PathVariable String freelanceId,
            @PathVariable String projectId
    ) {
        String message = "Projet demandé dans le portfolio.";

        return ResponseEntity.ok(getOkPortfolioResponseDto(
                portfolioService.getProjectToPortfolio(freelanceId, projectId),
                message, HttpStatus.OK));
    }

    @GetMapping
    public ResponseEntity<ResponseDto> listPortfolioProjects(@PathVariable String freelanceId) {
        String message = "La liste des projets du Freelance dans son portfolio.";
        return ResponseEntity.ok(getOkPortfoliosResponsesDto(portfolioService.listPortfolioProjects(freelanceId), message));
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<ResponseDto> deleteProjectFromPortfolio(
            @PathVariable String freelanceId,
            @PathVariable String projectId
    ) {
        String message = "Projet demandé a été supprimé du portfolio.";
        portfolioService.deleteProjectFromPortfolio(freelanceId, projectId);
        return ResponseEntity.ok(getOkResponse(null,  message, HttpStatus.OK));
    }

    public static ResponseDto getOkPortfoliosResponsesDto(List<Portfolio> portfolios, String message) {

        return getOkResponse(PortfolioResponseMapper.toResponseList(portfolios), message, HttpStatus.OK);
    }

    public static ResponseDto getOkPortfolioResponseDto(Portfolio portfolio, String message, HttpStatus status) {
        PortfolioResponse portfolioDto = PortfolioResponseMapper.toResponse(portfolio);

        return getOkResponse( portfolioDto, message, status);
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
