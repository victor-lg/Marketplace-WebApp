package com.bank.repositories;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bank.domains.Transaction;

public interface BankTransactionRepository extends CrudRepository<Transaction, String>{
	public Transaction findTransactionById(String id);
	public List <Transaction> findTransactionByTarjeta(BigInteger tarjeta);
	
}
