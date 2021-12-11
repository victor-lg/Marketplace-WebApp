package com.finance.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.finance.domains.*;
import com.finance.repositories.TransactionDAO;



@RestController
@CrossOrigin
@EnableAutoConfiguration
@RequestMapping("/finance")
public class FinanceController {

	@Autowired
	TransactionDAO transactionRepository;
	
	@PostMapping(value="/transactions", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> createTransaction(@RequestBody Transaction transaction){
		try {
			transactionRepository.save(transaction);
			return new ResponseEntity<>(transaction, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
		}
		
		
	}
	
	@RequestMapping(value = "/transactions", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getPedidos() {

		try {
			Iterable<Transaction> entityList = transactionRepository.findAll();

			return new ResponseEntity<>(entityList, HttpStatus.OK);

		} catch (Exception ex) {

			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

}














