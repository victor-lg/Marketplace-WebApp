package com.bank.repositories;

import org.springframework.data.repository.CrudRepository;

import com.bank.domains.BankTransaction;

public interface BankToFinanceTransactionRepository extends CrudRepository<BankTransaction, String>{

}
