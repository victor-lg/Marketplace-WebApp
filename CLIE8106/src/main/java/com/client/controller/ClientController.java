package com.client.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.client.domains.User;
import com.client.repositories.UserRepository;

@RestController
@CrossOrigin
@EnableAutoConfiguration
@RequestMapping("/client")

public class ClientController {

	@Autowired
	UserRepository userRepository;
	
	@PostMapping(value = "/newUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> newClient(@RequestBody User newUser) {
		try {
			userRepository.save(newUser);
			return new ResponseEntity<>(newUser, HttpStatus.OK);

		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}
	
	@RequestMapping(value = "/user", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getClientByName(@RequestParam(value = "mail", required = true) String mail) {

		try {
			User found=userRepository.findUserByMail(mail);
			if(found != null) {
				return new ResponseEntity<>(found, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}


		} catch (Exception ex) {

			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}
	
	@RequestMapping(value = "/users", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getAllUsers() {

		try {
			Iterable<User> found=userRepository.findAll();
			if(found != null) {
				return new ResponseEntity<>(found, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}


		} catch (Exception ex) {

			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}
	
	
	
	@RequestMapping(value = "/usersByName",method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getClientsByName(@RequestParam(value = "name", required = true) String name) {

		try {
			List <User> foundUsers=userRepository.findUsersByName(name);
			if(foundUsers != null) {
				return new ResponseEntity<>(foundUsers, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}


		} catch (Exception ex) {

			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}
	
	@RequestMapping(value = "/usersByCity",method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getClientsByCity(@RequestParam(value = "city", required = true) String city) {

		try {
			List <User> foundUsers=userRepository.findUsersByCity(city);
			if(foundUsers != null) {
				return new ResponseEntity<>(foundUsers, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}


		} catch (Exception ex) {

			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/modifyUser", consumes = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> changeClient(@RequestBody User updatedUser) {

		try {
			User found=userRepository.findUserByMail(updatedUser.getMail());
			if(found != null) {
				userRepository.delete(found);
				userRepository.save(updatedUser);
				return new ResponseEntity<>(updatedUser, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}


		} catch (Exception ex) {

			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/deleteUser",method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<?> deleteClient(@RequestParam(value = "mail", required = true) String mail) {

		try {
			User found=userRepository.findUserByMail(mail);
			if(found != null) {
				userRepository.delete(found);
				return new ResponseEntity<>(found, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}


		} catch (Exception ex) {

			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
