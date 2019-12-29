package com.example.demo.service;

import com.example.demo.model.Transaction;
import com.example.demo.model.User;
import com.google.common.util.concurrent.RateLimiter;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ClydesCardsLookupService {
    CompletableFuture<List<User>> getSystemUsers(RateLimiter rateLimiter);
    CompletableFuture<List<Transaction>> sendGetUserTransactionsRequest(User user, RateLimiter rateLimiter);
}
