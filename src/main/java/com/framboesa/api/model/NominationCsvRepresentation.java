package com.bramboesa.api.model;

import com.opencsv.bean.CsvBindByName;

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
public class NominationCsvRepresentation {

    @CsvBindByName(column = "year")
    private Integer year;

    @CsvBindByName(column = "title")
    private String title;

    @CsvBindByName(column = "producers")
    private String producers;

    @CsvBindByName(column = "winner")
    private String winner;
    
}
