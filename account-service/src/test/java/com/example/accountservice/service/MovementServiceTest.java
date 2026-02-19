package com.example.accountservice.service;

import com.example.accountservice.entity.*;
import com.example.accountservice.repository.AccountRepository;
import com.example.accountservice.repository.MovementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MovementServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private MovementRepository movementRepository;

    @InjectMocks
    private MovementService movementService;

    private Account account;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        account = Account.builder()
                .id(1L)
                .currentBalance(new BigDecimal("500"))
                .build();
    }

    @Test
    void shouldAddBalanceWhenCredit() {

        when(accountRepository.findById(1L))
                .thenReturn(Optional.of(account));

        Movement movement = movementService.createMovement(
                1L,
                MovementType.CREDIT,
                new BigDecimal("200")
        );

        assertEquals(new BigDecimal("700"), account.getCurrentBalance());
    }

    @Test
    void shouldSubtractBalanceWhenDebit() {

        when(accountRepository.findById(1L))
                .thenReturn(Optional.of(account));

        movementService.createMovement(
                1L,
                MovementType.DEBIT,
                new BigDecimal("100")
        );

        assertEquals(new BigDecimal("400"), account.getCurrentBalance());
    }

    @Test
    void shouldThrowWhenNoBalance() {

        when(accountRepository.findById(1L))
                .thenReturn(Optional.of(account));

        assertThrows(RuntimeException.class, () ->
                movementService.createMovement(
                        1L,
                        MovementType.DEBIT,
                        new BigDecimal("1000")
                )
        );
    }

    @Test
    void shouldThrowWhenValueIsZero() {

        assertThrows(IllegalArgumentException.class, () ->
                movementService.createMovement(
                        1L,
                        MovementType.CREDIT,
                        BigDecimal.ZERO
                )
        );
    }
}
