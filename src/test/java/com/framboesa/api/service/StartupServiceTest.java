package com.framboesa.api.service;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import com.framboesa.api.model.Nomination;
import com.framboesa.api.util.FileUtil;
import com.opencsv.exceptions.CsvValidationException;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class StartupServiceTest {

    @Mock
    private NominationService nominationService;

    @Mock
    private WinnerService winnerService;

    @InjectMocks
    private StartupService startupService;

    @Test
    void shouldReadCsvAndPersistDataOnStartup() throws Exception {
        // Arrange
        Nomination n1 = mock(Nomination.class);
        Nomination n2 = mock(Nomination.class);

        when(n1.isWinner()).thenReturn(true);
        when(n2.isWinner()).thenReturn(false);

        Set<Nomination> csvRecords = Set.of(n1, n2);

        try (var mockedFileUtil = mockStatic(FileUtil.class)) {

            mockedFileUtil
                .when(() -> FileUtil.formatFile(anyString()))
                .thenAnswer(inv -> null);

            mockedFileUtil
                .when(() -> FileUtil.readCsv(anyString()))
                .thenReturn(csvRecords);

            // Act
            startupService.executeOnStartup();

            // Assert
            verify(nominationService)
                .saveAll(new ArrayList<>(csvRecords));

            verify(winnerService)
                .saveAll(List.of(n1));
        }
    }

    @Test
    void shouldHandleEmptyCsvFile() throws Exception {
        // Arrange
        Set<Nomination> emptySet = Set.of();

        try (var mockedFileUtil = mockStatic(FileUtil.class)) {

            mockedFileUtil
                .when(() -> FileUtil.formatFile(anyString()))
                .thenAnswer(inv -> null);

            mockedFileUtil
                .when(() -> FileUtil.readCsv(anyString()))
                .thenReturn(emptySet);

            // Act
            startupService.executeOnStartup();

            // Assert
            verify(nominationService)
                .saveAll(new ArrayList<>(emptySet));

            verify(winnerService)
                .saveAll(List.of());
        }
    }
}