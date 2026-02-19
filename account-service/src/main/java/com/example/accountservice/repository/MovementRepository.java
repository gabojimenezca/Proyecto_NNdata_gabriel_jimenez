package com.example.accountservice.repository;

import com.example.accountservice.entity.Movement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import java.time.LocalDateTime;
import java.util.List;

public interface MovementRepository extends JpaRepository<Movement, Long> {

    List<Movement> findByAccountIdAndDateBetweenOrderByDateDesc(
            Long accountId,
            LocalDateTime start,
            LocalDateTime end
    );

    List<Movement> findByAccountIdOrderByDateDesc(Long accountId);
}
