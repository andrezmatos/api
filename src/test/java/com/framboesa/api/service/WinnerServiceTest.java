package com.framboesa.api.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.framboesa.api.dto.NominationsDTO;
import com.framboesa.api.dto.WinnerYearDiffDTO;
import com.framboesa.api.model.Nomination;
import com.framboesa.api.model.Winner;
import com.framboesa.api.repository.WinnerRepository;
import com.framboesa.api.util.WinnerConverter;

@ExtendWith(MockitoExtension.class)
class WinnerServiceTest {

    @Mock
    private WinnerRepository winnerRepository;

    @InjectMocks
    private WinnerService winnerService;


    @Test
    void shouldConvertNominationsAndSaveWinners() {
        Nomination nomination = new Nomination();
        List<Nomination> nominations = List.of(nomination);

        Winner winner = new Winner();
        List<Winner> convertedWinners = List.of(winner);

        try (var mockedConverter = mockStatic(WinnerConverter.class)) {
            mockedConverter
                .when(() -> WinnerConverter.convNominationToWinner(nomination))
                .thenReturn(convertedWinners);

            mockedConverter
                .when(() -> WinnerConverter.printString(any(Winner.class)))
                .thenAnswer(inv -> null);

            winnerService.saveAll(nominations);

            ArgumentCaptor<List<Winner>> captor = ArgumentCaptor.forClass(List.class);
            verify(winnerRepository).saveAll(captor.capture());

            List<Winner> savedWinners = captor.getValue();
            assertEquals(1, savedWinners.size());
            assertSame(winner, savedWinners.get(0));
        }
    }

    @Test
    void shouldHandleEmptyNominationList() {

        List<Nomination> nominations = List.of();

        winnerService.saveAll(nominations);

        verify(winnerRepository).saveAll(List.of());
    }


    @Test
    void shouldReturnMaxAndMinWinnerYearDiffDTO() {
        WinnerYearDiffDTO maxDto = mock(WinnerYearDiffDTO.class);
        when(maxDto.getProducer()).thenReturn("Producer A");
        when(maxDto.getYearDiff()).thenReturn(10);

        WinnerYearDiffDTO minDto = mock(WinnerYearDiffDTO.class);
        when(minDto.getProducer()).thenReturn("Producer B");
        when(minDto.getYearDiff()).thenReturn(1);

        when(winnerRepository.findMaxYearDifferences())
            .thenReturn(List.of(maxDto));

        when(winnerRepository.findMinYearDifferences())
            .thenReturn(List.of(minDto));
            
        NominationsDTO result = winnerService.gWinnerYearDiffDTO();

        assertNotNull(result);

        assertEquals(1, result.getMax().size());
        assertEquals("Producer A", result.getMax().get(0).getProducer());
        assertEquals(10, result.getMax().get(0).getYearDiff());

        assertEquals(1, result.getMin().size());
        assertEquals("Producer B", result.getMin().get(0).getProducer());
        assertEquals(1, result.getMin().get(0).getYearDiff());

        verify(winnerRepository).findMaxYearDifferences();
        verify(winnerRepository).findMinYearDifferences();
    }
}
