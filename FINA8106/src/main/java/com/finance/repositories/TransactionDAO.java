package com.finance.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.finance.domains.Transaction;

public interface TransactionDAO extends CrudRepository<Transaction, String> {

	public Transaction findTransactionById(String id);
	public List<Transaction> findTransactionsByTarjeta(Long tarjeta);
}
