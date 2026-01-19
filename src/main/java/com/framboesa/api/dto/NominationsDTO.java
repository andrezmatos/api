package com.framboesa.api.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NominationsDTO {
    private List<WinnerYearDiffDTO> max;

    private List<WinnerYearDiffDTO> min;
}
