package com.framboesa.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.framboesa.api.dto.NominationDTO;
import com.framboesa.api.model.Nomination;
import com.framboesa.api.repository.NominationRepository;
import com.framboesa.api.util.NominationConverter;

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

    @SuppressWarnings("null")
    public void saveAll(List<Nomination> records) {
        repository.saveAll(records);
    }
    
}
