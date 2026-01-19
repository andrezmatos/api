package com.framboesa.api.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.framboesa.api.model.Winner;
import com.framboesa.api.repository.WinnerRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class NominationControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WinnerRepository winnerRepository;

    @Test
    void shouldReturnMaxAndMinLists() throws Exception {
        mockMvc.perform(get("/nomination"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.max").exists())
            .andExpect(jsonPath("$.min").exists())
            .andExpect(jsonPath("$.max").isArray())
            .andExpect(jsonPath("$.min").isArray());
    }

   @Test
    void shouldReturnEmptyListsWhenNoWinners() throws Exception {
        mockMvc.perform(get("/nomination"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.max").isEmpty())
            .andExpect(jsonPath("$.min").isEmpty());
    }


    @Test
    void shouldCalculateMinAndMaxIntervalsCorrectly() throws Exception {
        saveWinner("Pixar", 2000);
        saveWinner("Pixar", 2005);
        saveWinner("Pixar", 2015); // intervalos: 5 e 10

        mockMvc.perform(get("/nomination"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.min[0].producer").value("Pixar"))
            .andExpect(jsonPath("$.min[0].yearDiff").value(5))
            .andExpect(jsonPath("$.max[0].producer").value("Pixar"))
            .andExpect(jsonPath("$.max[0].yearDiff").value(10));
    }

    private void saveWinner(
        String producer,
        int year
    ) {
        var entity = Winner.builder()
            .producer(producer)
            .title("Movie " + year)
            .year(year)
            .build();

        winnerRepository.save(entity);
    }


}
