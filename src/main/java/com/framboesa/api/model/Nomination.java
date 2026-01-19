package com.framboesa.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
@Entity
public class Nomination {

    @Id
    @GeneratedValue
    private Integer Id;

    @Column(name = "production_year") 
    private Integer  year;

    private String title;

    private String producers;

    @Column(nullable = true)
    private boolean winner;

}
