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


import com.bank.business.*;
import com.bank.domains.Transaction;
import com.bank.domains.TransactionFromClient;
import com.bank.repositories.BankToFinanceTransactionRepository;
import com.bank.repositories.BankTransactionRepository;


@RestController
@CrossOrigin
@EnableAutoConfiguration
@RequestMapping("/bank")

public class BankController {
	
	@Autowired
	BankTransactionRepository transactionRepository;
	
	@Autowired
	BankToFinanceTransactionRepository bankToFinanceRepository;
	
	@PostMapping(value = "/transaccion", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> getAuthorization(@RequestBody TransactionFromClient transactionFromClient) {

		ValidacionTarjeta _validacionTarjeta = new ValidacionTarjeta();
		
		System.out.println(_validacionTarjeta.validarTarjeta(transactionFromClient.getFechaTarjeta(), transactionFromClient.getCv2(),
				transactionFromClient.getTarjeta()));

		if (_validacionTarjeta.validarTarjeta(transactionFromClient.getFechaTarjeta(), transactionFromClient.getCv2(),
				transactionFromClient.getTarjeta())) {
			Transaction transaction=new Transaction();

			// La tarjeta es valida. Vamos a guardar el pedido y la transaccion en sus respectivos repositorios
			transactionRepository.save(transaction);

			CodigoTransaccion _codTransacion = new CodigoTransaccion();

			String _codigoGeneradoDeTransaccion = _codTransacion.generacionCodigoTransaccion(transaction.getFechaTarjeta());

			/*AuthorizationTransacionPedido _authorizationTransacionPedido = new AuthorizationTransacionPedido(
					_codigoGeneradoDeTransaccion, authorization.getCodPedido(), authorization.getCoste(),
					authorization.getFechaTarjeta(), authorization.getCv2(), authorization.getNumeroTarjeta());

			TransaccionConciliacion _transaccionConciliacion = new TransaccionConciliacion(_codigoGeneradoDeTransaccion,
					String.valueOf(authorization.getCoste()), authorization.getFechaTarjeta());

			transaccionConciliacionRepository.save(_transaccionConciliacion);

			// Si quermos guardar _authorizationTransacionPedido en la coleccion de financiera
			authorizationTransacionPedidoRepository.save(_authorizationTransacionPedido);*/

			return new ResponseEntity<>(HttpStatus.OK);

		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	

}
