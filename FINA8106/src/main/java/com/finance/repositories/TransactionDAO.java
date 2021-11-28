package com.finance.repositories;

import org.springframework.data.repository.CrudRepository;

import com.finance.domains.Transaction;

public interface TransactionDAO extends CrudRepository<Transaction, String> {

	public Transaction findTransactionById(String id);
	public Transaction findTransactionByTarjeta(Long tarjeta);
}
