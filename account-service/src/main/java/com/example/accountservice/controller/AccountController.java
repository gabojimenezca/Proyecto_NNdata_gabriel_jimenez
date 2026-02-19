package com.example.accountservice.controller;

import com.example.accountservice.entity.Account;
import com.example.accountservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService service;

    @GetMapping
    public List<Account> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Account getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public Account create(@RequestBody Account account) {
        return service.save(account);
    }

    @PutMapping("/{id}")
    public Account update(@PathVariable Long id, @RequestBody Account account) {
        return service.update(id, account);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
