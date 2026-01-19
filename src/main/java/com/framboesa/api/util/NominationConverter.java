package com.bramboesa.api.util;

import com.bramboesa.api.dto.NominationDTO;
import com.bramboesa.api.model.Nomination;

public class NominationConverter {
    
    static public Nomination convert(NominationDTO nominationDTO) {
        return Nomination.builder().Id(nominationDTO.getId())
                                    .year(nominationDTO.getYear())
                                    .title(nominationDTO.getProducers())
                                    .producers(nominationDTO.getProducers())
                                    .winner(nominationDTO.isWinner())
                                    .build();
    }
    
    static public NominationDTO convert(Nomination nomination) {
        return NominationDTO.builder().Id(nomination.getId())
                                    .year(nomination.getYear())
                                    .title(nomination.getProducers())
                                    .producers(nomination.getProducers())
                                    .winner(nomination.isWinner())
                                    .build();
    }
}
