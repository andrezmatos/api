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
public class WinnerDTO {
    private Long Id;

    private Integer year;

    private String title;

    private String producer;
    
}
