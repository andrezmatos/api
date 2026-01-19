package com.framboesa.api.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import com.framboesa.api.dto.NominationDTO;
import com.framboesa.api.model.Nomination;
import com.framboesa.api.repository.NominationRepository;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class NominationServiceTest {

    @Mock
    private NominationRepository repository;

    @InjectMocks
    private NominationService service;

    @Test
    void shouldSaveNominationAndReturnDTO() {
        var dto = NominationDTO.builder()
            .producers("Pixar")
            .title("Toy Story")
            .year(2000)
            .winner(true)
            .build();

        var entity = Nomination.builder()
            .Id(1)
            .producers("Pixar")
            .title("Toy Story")
            .year(2000)
            .winner(true)
            .build();

        when(repository.save(any(Nomination.class)))
            .thenReturn(entity);

        var result = service.saveNomination(dto);

        assertThat(result).isNotNull();
        assertThat(result.getProducers()).isEqualTo("Pixar");
        assertThat(result.getTitle()).isEqualTo("Toy Story");
        assertThat(result.getYear()).isEqualTo(2000);
        assertThat(result.isWinner()).isTrue();

        verify(repository).save(any(Nomination.class));
    }

    @Test
    void shouldReturnEmptyNominationsDTO() {
        var result = service.getNominations();

        assertThat(result).isNotNull();
        assertThat(result.getMax()).isNull();
        assertThat(result.getMin()).isNull();
    }

    @Test
    void shouldSaveAllNominations() {

        var records = List.of(
            Nomination.builder()
                .producers("Pixar")
                .title("Movie 1")
                .year(2000)
                .winner(true)
                .build(),
            Nomination.builder()
                .producers("Pixar")
                .title("Movie 2")
                .year(2005)
                .winner(true)
                .build()
        );

        service.saveAll(records);

        verify(repository).saveAll(records);
    }
}

