package com.framboesa.api.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Resposta contendo intervalos mínimos e máximos")
public class NominationsSwaggerDTO {

    @Schema(description = "Produtores com maior intervalo entre vitórias")
    private List<WinnerYearDiffSwaggerDTO> max;

    @Schema(description = "Produtores com menor intervalo entre vitórias")
    private List<WinnerYearDiffSwaggerDTO> min;
}
