package com.example.accountservice.controller;

import com.example.accountservice.dto.MovementRequest;
import com.example.accountservice.entity.Movement;
import com.example.accountservice.service.MovementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.time.LocalDate;


@RestController
@RequestMapping("/api/v1/movements")
@RequiredArgsConstructor
public class MovementController {

    private final MovementService service;

    @PostMapping
    public Movement create(@RequestBody MovementRequest request) {
        return service.createMovement(
                request.getAccountId(),
                request.getType(),
                request.getValue()
        );
    }

    @GetMapping("/account/{accountId}")
    public List<Movement> getByAccount(@PathVariable Long accountId) {
        return service.getMovementsByAccount(accountId);
    }

    @GetMapping("/report")
    public List<Movement> getReport(
            @RequestParam Long accountId,
            @RequestParam String start,
            @RequestParam String end
    ) {

        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);

        return service.getReport(accountId, startDate, endDate);
    }
}
