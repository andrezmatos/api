package com.framboesa.api.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.framboesa.api.model.Nomination;
import com.framboesa.api.util.FileUtil;
import com.opencsv.exceptions.CsvValidationException;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Profile("!test")
public class StartupService {

    private final NominationService nominationService;

    private final WinnerService winnerService;

    private String path = "D:\\Documents\\avaliacao\\movielist.csv";

    @PostConstruct
    public void executeOnStartup() throws CsvValidationException, IOException{
        FileUtil.formatFile(path);
        List<Nomination> records = new ArrayList<>(FileUtil.readCsv(path));
        System.out.println(String.format("NUMERO DE REGISTROS: %d", records.size()));
        nominationService.saveAll(records);
        List<Nomination> winners = records.stream().filter(Nomination::isWinner).toList();
        System.out.println(String.format("NUMERO DE REGISTROS VENCEDORES: %d", winners.size()));
        winnerService.saveAll(winners);
    }

}
