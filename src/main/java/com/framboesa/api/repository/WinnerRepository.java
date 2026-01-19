package com.framboesa.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.framboesa.api.dto.WinnerYearDiffDTO;
import com.framboesa.api.model.Winner;

@Repository
public interface WinnerRepository extends JpaRepository<Winner, Long>  {

@Query(value = """
SELECT A.producer,
            A.productionYear,
          CASE WHEN A.yearDiff IS NOT NULL THEN A.yearDiff ELSE 0 END AS yearDiff,
          A.previousWin
FROM (SELECT
        producer AS producer,
        production_year AS productionYear,
        production_year
          - LAG(production_year)
            OVER (PARTITION BY producer ORDER BY production_year) AS yearDiff,
      CASE WHEN LAG(production_year) OVER (PARTITION BY producer ORDER BY production_year) IS NOT NULL
      THEN LAG(production_year) OVER (PARTITION BY producer ORDER BY production_year) ELSE 0 END AS previousWin
    FROM winner
    WHERE production_year IS NOT NULL
    ORDER BY producer, production_year) A
    WHERE yearDiff > 0
    ORDER BY A.yearDiff DESC
LIMIT 2
""", nativeQuery = true)
List<WinnerYearDiffDTO> findMaxYearDifferences();

@Query(value = """
SELECT A.producer,
            A.productionYear,
          CASE WHEN A.yearDiff IS NOT NULL THEN A.yearDiff ELSE 0 END AS yearDiff,
          A.previousWin
FROM (SELECT
        producer AS producer,
        production_year AS productionYear,
        production_year
          - LAG(production_year)
            OVER (PARTITION BY producer ORDER BY production_year) AS yearDiff,
      CASE WHEN LAG(production_year) OVER (PARTITION BY producer ORDER BY production_year) IS NOT NULL
      THEN LAG(production_year) OVER (PARTITION BY producer ORDER BY production_year) ELSE 0 END AS previousWin
    FROM winner
    WHERE production_year IS NOT NULL
    ORDER BY producer, production_year) A
    WHERE yearDiff > 0
    ORDER BY A.yearDiff
LIMIT 2
""", nativeQuery = true)
List<WinnerYearDiffDTO> findMinYearDifferences();
    
}
