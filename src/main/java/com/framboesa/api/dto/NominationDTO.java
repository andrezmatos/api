package com.framboesa.api.dto;

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
public class NominationDTO {
    private Integer Id;

    private Integer year;

    private String title;

    private String producers;

    private boolean winner;
    
}
