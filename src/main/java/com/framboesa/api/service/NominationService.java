package com.bramboesa.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bramboesa.api.dto.NominationDTO;
import com.bramboesa.api.dto.NominationsDTO;
import com.bramboesa.api.model.Nomination;
import com.bramboesa.api.repository.NominationRepository;
import com.bramboesa.api.util.NominationConverter;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NominationService {

    private final NominationRepository repository;

    @SuppressWarnings("null")
    public NominationDTO saveNomination(NominationDTO nominationDTO) {
        Nomination nomination = repository.save(NominationConverter.convert(nominationDTO));
        return NominationConverter.convert(nomination);

    }

    public NominationsDTO getNominations() {
        NominationsDTO nominationsDTO = new NominationsDTO();

        return nominationsDTO;
    }

    @SuppressWarnings("null")
    public void saveAll(List<Nomination> records) {
        repository.saveAll(records);
    }
    
}
