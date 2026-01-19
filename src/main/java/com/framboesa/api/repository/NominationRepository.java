package com.framboesa.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.framboesa.api.model.Nomination;

@Repository
public interface NominationRepository extends JpaRepository<Nomination, Long> {
    
}
