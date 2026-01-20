package com.framboesa.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.framboesa.api.dto.NominationsDTO;
import com.framboesa.api.dto.NominationsSwaggerDTO;
import com.framboesa.api.service.WinnerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/nomination")
@RequiredArgsConstructor
@Tag(name = "Nominations", description = "Endpoints relacionados às indicações e vencedores")
public class NominationController {

    private final WinnerService winnerService;

    @Operation(
        summary = "Buscar intervalos entre vitórias",
        description = """
            Retorna os produtores com:
            - Maior intervalo entre vitórias consecutivas
            - Menor intervalo entre vitórias consecutivas
        """
    )
    @ApiResponse(
        responseCode = "500",
        description = "Erro interno ao processar os dados"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Intervalos calculados com sucesso",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = NominationsSwaggerDTO.class)
        )
    )
    @GetMapping
    public ResponseEntity<NominationsDTO> getNominations() {
        return ResponseEntity.ok(winnerService.gWinnerYearDiffDTO());
    }
}
