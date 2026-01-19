package com.framboesa.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.framboesa.api.dto.NominationsDTO;
import com.framboesa.api.service.WinnerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/nomination")
@RequiredArgsConstructor
public class NominationController {

    final private WinnerService winnerService;

    @GetMapping
    public ResponseEntity<NominationsDTO> getNominations() {
        return ResponseEntity.ok(winnerService.gWinnerYearDiffDTO());
    }
}
