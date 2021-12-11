package com.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bank.business.*;
import com.bank.domains.BankTransaction;
import com.bank.domains.Transaction;
import com.bank.domains.TransactionFromClient;
import com.bank.repositories.BankToFinanceTransactionRepository;
import com.bank.repositories.BankTransactionRepository;

import java.math.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
@CrossOrigin
@EnableAutoConfiguration
@RequestMapping("/bank")

public class BankController {
	
	@Autowired
	BankTransactionRepository transactionRepository;
	
	@Autowired
	BankToFinanceTransactionRepository bankToFinanceRepository;
	
	@Autowired
	RestTemplate restTemplate;
	
	public void sendToFinance(BankTransaction transactionForFinance) {
		restTemplate.postForObject("http://localhost:10604/finance/transactions", transactionForFinance, BankTransaction.class);
		
	}
	
	@PostMapping(value = "/transactions", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> getAuthorization(@RequestBody TransactionFromClient transactionFromClient) {
		try {
			ValidacionTarjeta _validacionTarjeta = new ValidacionTarjeta();
			
			System.out.println(_validacionTarjeta.validarTarjeta(transactionFromClient.getFechaTarjeta(), transactionFromClient.getCv2(),
					transactionFromClient.getTarjeta()));

			if (_validacionTarjeta.validarTarjeta(transactionFromClient.getFechaTarjeta(), transactionFromClient.getCv2(),
					transactionFromClient.getTarjeta())) {
				

				CodigoTransaccion _codTransacion = new CodigoTransaccion();
				DateFormat objSDF = new SimpleDateFormat("yyyyMMdd");
				Date _fechaActual = new Date();
				
				String _codigoGeneradoDeTransaccion = _codTransacion.generacionCodigoTransaccion(transactionFromClient.getFechaTarjeta());
				
				Transaction transaction=new Transaction(_codigoGeneradoDeTransaccion, 
						transactionFromClient.getCodigoPedido(), 
						transactionFromClient.getCoste(),
						transactionFromClient.getCv2(),
						transactionFromClient.getFechaTarjeta(), 
						BigInteger.valueOf(transactionFromClient.getTarjeta()));

						// La tarjeta es valida. Vamos a guardar el pedido y la transaccion en sus respectivos repositorios
				transactionRepository.save(transaction);
				
				BankTransaction transactionForFinance= new BankTransaction(_codigoGeneradoDeTransaccion,
						transaction.getCodPedido(), 
						transactionFromClient.getComprador(), 
						transactionFromClient.getVendedor(), 
						transaction.getCoste(), 
						transaction.getFechaTarjeta(), 
						objSDF.format(_fechaActual).toString(), 
						transaction.getCv2(), 
						transaction.getTarjeta().longValue());
				
				sendToFinance(transactionForFinance);

				return new ResponseEntity<>(transaction, HttpStatus.OK);

			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		
	}
	

}
