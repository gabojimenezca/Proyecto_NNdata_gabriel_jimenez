package com.example.accountservice.service;

import com.example.accountservice.entity.*;
import com.example.accountservice.repository.AccountRepository;
import com.example.accountservice.repository.MovementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class MovementService {

    private final AccountRepository accountRepository;
    private final MovementRepository movementRepository;

    public Movement createMovement(Long accountId, MovementType type, BigDecimal value) {

        // 1️⃣ Validar valor > 0
        if (value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El valor debe ser mayor a cero");
        }

        // 2️⃣ Buscar cuenta
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));



        BigDecimal newBalance;

        // 3️⃣ Lógica de débito
        if (type == MovementType.DEBIT) {

            if (account.getCurrentBalance().compareTo(value) < 0) {
                throw new RuntimeException("Saldo no disponible");
            }

            newBalance = account.getCurrentBalance().subtract(value);

        } else { // 4️⃣ Crédito

            newBalance = account.getCurrentBalance().add(value);
        }

        // 5️⃣ Actualizar saldo en cuenta
        account.setCurrentBalance(newBalance);
        accountRepository.save(account);

        // 6️⃣ Registrar movimiento
        Movement movement = Movement.builder()
                .date(LocalDateTime.now())
                .type(type)
                .value(value)
                .balance(newBalance)
                .account(account)
                .build();



        return movementRepository.save(movement);
    }

    public List<Movement> getMovementsByAccount(Long accountId) {
        return movementRepository.findByAccountIdOrderByDateDesc(accountId);
    }

    public List<Movement> getReport(Long accountId, LocalDate start, LocalDate end) {

        LocalDateTime startDateTime = start.atStartOfDay();
        LocalDateTime endDateTime = end.atTime(23, 59, 59);

        return movementRepository
                .findByAccountIdAndDateBetweenOrderByDateDesc(
                        accountId,
                        startDateTime,
                        endDateTime
                );
    }

}
