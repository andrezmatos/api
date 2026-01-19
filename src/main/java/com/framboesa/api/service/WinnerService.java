package com.bramboesa.api.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import com.bramboesa.api.dto.NominationsDTO;
import com.bramboesa.api.dto.WinnerYearDiffDTO;
import com.bramboesa.api.model.Nomination;
import com.bramboesa.api.model.Winner;
import com.bramboesa.api.repository.WinnerRepository;
import com.bramboesa.api.util.WinnerConverter;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WinnerService {

    private final WinnerRepository winnerRepository;
 
    public void saveAll(List<Nomination> records) {
        List<Winner> winners = new ArrayList<>();
        
        for (Nomination nomination : records) {
            List<Winner> convNominationToWinner = WinnerConverter.convNominationToWinner(nomination);
            winners.addAll(convNominationToWinner);
        }
        for (Winner winner : winners) {
            WinnerConverter.printString(winner);
        }
        winnerRepository.saveAll(winners);
    }

    public NominationsDTO gWinnerYearDiffDTO() {
        List<WinnerYearDiffDTO> maxWinnerYearDiffDTOs =  winnerRepository.findMaxYearDifferences();
        List<WinnerYearDiffDTO> minWinnerYearDiffDTOs =  winnerRepository.findMinYearDifferences();
        return NominationsDTO.builder().max(maxWinnerYearDiffDTOs).min(minWinnerYearDiffDTOs).build();
    }
    //*
   
    
}
