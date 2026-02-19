package com.example.accountservice.service;

import com.example.accountservice.entity.Account;
import com.example.accountservice.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class AccountService {
    private final AccountRepository repository;

    public List<Account> findAll() {
        return repository.findAll();
    }

    public Account findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
    }

    public Account save(Account account) {
        account.setCurrentBalance(account.getInitialBalance());
        return repository.save(account);
    }

    public Account update(Long id, Account account) {
        Account existing = findById(id);
        existing.setType(account.getType());
        existing.setStatus(account.getStatus());
        return repository.save(existing);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
