package com.framboesa.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Diferença de anos entre vitórias consecutivas")
public class WinnerYearDiffSwaggerDTO {

    @Schema(example = "Producer A")
    private String producer;

    @Schema(example = "2001")
    private Integer previousWin;

    @Schema(example = "2005")
    private Integer productionYear;

    @Schema(example = "4")
    private Integer yearDiff;
}
