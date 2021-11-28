package com.bank.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bank.domains.BankTransaction;

public interface BankTransactionRepository extends CrudRepository<BankTransaction, String>{
	public BankTransaction findTransactionById(String id);
	public List <BankTransaction> findTransactionByTarjeta(Long tarjeta);
}
