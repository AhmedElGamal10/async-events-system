package com.example.demo.service;

import com.example.demo.model.transaction.Transaction;
import com.example.demo.model.transaction.TransactionEvent;
import com.example.demo.model.transaction.TransactionEvent.TransactionEventType;
import com.example.demo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.example.demo.util.DateUtils.getCurrentDate;

@Service
public class UserTransactionsHandlingServiceImpl implements UserTransactionsHandlingService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private EventSenderService eventSenderService;

    @Transactional
    public CompletableFuture<Void> handleTransactionEvents(TransactionEvent transactionEvent) {
        return CompletableFuture.allOf(transactionRepository.saveTransaction(transactionEvent.getTransaction()),
                eventSenderService.sendEvent(transactionEvent));
    }

    public List<TransactionEvent> resolveConflicts(List<Transaction> remoteTransactions, List<Transaction> savedTransactions) {
        Map<String, Transaction> savedTransactionsMap = savedTransactions.stream().collect(Collectors.toMap(Transaction::getId, Function.identity()));

        return remoteTransactions.stream().filter(x -> !x.equals(savedTransactionsMap.getOrDefault(x.getId(), null)))
                .map(x -> new TransactionEvent(x, savedTransactionsMap.containsKey(x.getId()) ? TransactionEventType.UPDATE : TransactionEventType.CREATE, getCurrentDate()))
                .collect(Collectors.toList());
    }
}
