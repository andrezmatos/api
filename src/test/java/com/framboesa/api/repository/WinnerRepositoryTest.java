package com.framboesa.api.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.framboesa.api.dto.WinnerYearDiffDTO;
import com.framboesa.api.model.Winner;

import jakarta.persistence.EntityManager;

@DataJpaTest
@ActiveProfiles("test")
class WinnerRepositoryTest {

    @Autowired
    private WinnerRepository winnerRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void shouldReturnMaxYearDifferences() {

        persistWinner("Producer A", 2000);
        persistWinner("Producer A", 2010);
        persistWinner("Producer A", 2020);

        persistWinner("Producer B", 1995);
        persistWinner("Producer B", 2000);
        persistWinner("Producer B", 2015);

        entityManager.flush();
        entityManager.clear();


        List<WinnerYearDiffDTO> result =
            winnerRepository.findMaxYearDifferences();


        assertEquals(2, result.size());

        WinnerYearDiffDTO first = result.get(0);
        WinnerYearDiffDTO second = result.get(1);

        assertEquals("Producer B", first.getProducer());
        assertEquals(15, first.getYearDiff());

        assertEquals("Producer A", second.getProducer());
        assertEquals(10, second.getYearDiff());
    }

    @Test
    void shouldReturnMinYearDifferences() {
        // Arrange
        persistWinner("Producer A", 2000);
        persistWinner("Producer A", 2010); // diff = 10
        persistWinner("Producer A", 2020); // diff = 10

        persistWinner("Producer B", 1995);
        persistWinner("Producer B", 2000); // diff = 5
        persistWinner("Producer B", 2015); // diff = 15

        entityManager.flush();
        entityManager.clear();

        // Act
        List<WinnerYearDiffDTO> result =
            winnerRepository.findMinYearDifferences();

        // Assert
        assertEquals(2, result.size());

        WinnerYearDiffDTO first = result.get(0);
        WinnerYearDiffDTO second = result.get(1);

        assertEquals("Producer B", first.getProducer());
        assertEquals(5, first.getYearDiff());

        assertEquals("Producer A", second.getProducer());
        assertEquals(10, second.getYearDiff());
    }

    private void persistWinner(String producer, int year) {
        Winner winner = Winner.builder()
            .producer(producer)
            .year(year)
            .build();

        entityManager.persist(winner);
    }
}
