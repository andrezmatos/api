package com.framboesa.api.util;

import com.framboesa.api.dto.NominationDTO;
import com.framboesa.api.model.Nomination;

public class NominationConverter {
    
    static public Nomination convert(NominationDTO nominationDTO) {
        return Nomination.builder().Id(nominationDTO.getId())
                                    .year(nominationDTO.getYear())
                                    .title(nominationDTO.getTitle())
                                    .producers(nominationDTO.getProducers())
                                    .winner(nominationDTO.isWinner())
                                    .build();
    }
    
    static public NominationDTO convert(Nomination nomination) {
        return NominationDTO.builder().Id(nomination.getId())
                                    .year(nomination.getYear())
                                    .title(nomination.getTitle())
                                    .producers(nomination.getProducers())
                                    .winner(nomination.isWinner())
                                    .build();
    }
}
