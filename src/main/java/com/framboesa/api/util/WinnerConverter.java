package com.bramboesa.api.util;

import java.util.ArrayList;
import java.util.List;

import com.bramboesa.api.dto.WinnerDTO;
import com.bramboesa.api.model.Nomination;
import com.bramboesa.api.model.Winner;

public class WinnerConverter {

    static public WinnerDTO convWinner(Winner winner) {
        return WinnerDTO.builder().Id(winner.getId())
                                .title(winner.getTitle())
                                .year(winner.getYear())
                                .producer(winner.getProducer())
                                .build();
    }

    static public Winner convWinnerDTO(WinnerDTO winnerDTO) {
        return Winner.builder().title(winnerDTO.getTitle())
                                .year(winnerDTO.getYear())
                                .producer(winnerDTO.getProducer())
                                .build();
    }

    static public List<Winner> convNominationToWinner(Nomination nomination) {
        List<Winner> winners = new ArrayList<>();
        String[] producers = nomination.getProducers().split("#");
        for (String producer : producers) {
            winners.add(Winner.builder().title(nomination.getTitle())
                                .year(nomination.getYear())
                                .producer(producer)
                                .build());
        }
        return winners;
    }

    static public List<WinnerDTO> convWinner(List<Winner> winners) {
        List<WinnerDTO> winnerDTOs = new ArrayList<>();
        winners.forEach(winner -> winnerDTOs.add(convWinner(winner)));
        return winnerDTOs;
    }

    static public List<Winner> convWinnerDTO(List<WinnerDTO> winnersDTO) {
        List<Winner> winners = new ArrayList<>();
        winnersDTO.forEach(winnerDTO -> winners.add(convWinnerDTO(winnerDTO)));
        return winners;
    }
    
    static public void printString(Winner winner) {
        System.out.println(String.format("Ano: %s Titulo: %s Produtor: %s ", winner.getYear(), winner.getTitle(), winner.getProducer()));
    }
}
