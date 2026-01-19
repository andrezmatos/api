package com.bramboesa.api.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.bramboesa.api.model.Nomination;
import com.bramboesa.api.model.NominationCsvRepresentation;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.exceptions.CsvValidationException;

public class FileUtil {

    static  public void formatFile(String path) {
        Path filePath = Paths.get(path);

        try {
            List<String> lines = Files.readAllLines(filePath);

            StringBuilder newContent = new StringBuilder();
            for (String line : lines) {
                newContent.append(line.replace(", ", "#").replace(" and ", "#").replace("and ", "#").replace("##", "#").replace(";", ","));
                newContent.append(System.lineSeparator());
            }

            Files.write(filePath, newContent.toString().getBytes(), StandardOpenOption.TRUNCATE_EXISTING);

            System.out.println("Arquivo modificado com sucesso!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static public Set<Nomination> readCsv(String path) throws IOException, CsvValidationException {
        HeaderColumnNameMappingStrategy<NominationCsvRepresentation> strategy = new HeaderColumnNameMappingStrategy<>();
        strategy.setType(NominationCsvRepresentation.class);
        try (Reader reader = new BufferedReader(new FileReader(path))) {
            CsvToBean<NominationCsvRepresentation> csvToBean = new CsvToBeanBuilder<NominationCsvRepresentation>(reader)
                                                                    .withMappingStrategy(strategy)
                                                                    .withIgnoreEmptyLine(true)
                                                                    .withIgnoreLeadingWhiteSpace(true)
                                                                    .build();
                    
            Set<Nomination> beans = csvToBean.parse()
                    .stream()
                    .map(csvLine -> Nomination.builder()
                                        .year(csvLine.getYear())
                                        .title(csvLine.getTitle())
                                        .producers(csvLine.getProducers())
                                        .winner(csvLine.getWinner() == null || csvLine.getWinner().isBlank() ? false : true)
                                        .build()
                        ).collect(Collectors.toSet());
            
            return beans;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
          
    }
    
}
