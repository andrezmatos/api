package com.bramboesa.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bramboesa.api.dto.NominationDTO;
import com.bramboesa.api.dto.NominationsDTO;
import com.bramboesa.api.service.NominationService;
import com.bramboesa.api.service.WinnerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/nomination")
@RequiredArgsConstructor
public class NominationController {

    final private NominationService service;

    final private WinnerService winnerService;
    
    @PostMapping
    public ResponseEntity<NominationDTO> insert(NominationDTO nominationDTO) {
        return ResponseEntity.ok(service.saveNomination(nominationDTO));
    }

    @GetMapping
    public ResponseEntity<NominationsDTO> getNominations() {
        return ResponseEntity.ok(winnerService.gWinnerYearDiffDTO());
    }
}
