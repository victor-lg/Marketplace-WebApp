package com.admin.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.admin.application.domains.Transaction;
import com.admin.application.domains.User;

@Controller
public class AdminController {
	@Autowired
	RestTemplate restTemplate;
	/**
	* Map by the default to the page index.html
	* @return la página index.html
	*/
	@RequestMapping("/")
	public String index(){
		return "index";
	}
	
	/*@RequestMapping (value = "pagina-usuario/{name}", method = RequestMethod.GET)
	public String returnUsuarios(Model model, @PathVariable String name) {
		User[] us = restTemplate.getForObject("http://localhost:10602/client/users",
				User[].class, name);
		model.addAttribute("usuarios", us);
		return "viewUsuarios";
	
	}*/
	
	
	//USERS
	
	@RequestMapping (value = "pagina-todos-usuarios", method = RequestMethod.GET)
	public String returnTodosUsuarios(Model model) {
		User[] listaUs = restTemplate.getForObject("http://localhost:10602/client/users",
		User[].class);
		model.addAttribute("usuarios", listaUs);
		return "viewUsuarios";
	}
	
	@RequestMapping (value="pagina-crear-usuario", method=RequestMethod.GET)
	public String mostrarElFormularioDelUsuario(Model modelo) {
		modelo.addAttribute("usuario", new User());
		return "ViewCrearUsuario";
	}
	
	@RequestMapping (value = "pagina-post-usuario", method = RequestMethod.POST)
	public String saveUser(Model model, @ModelAttribute User us) {
		restTemplate.postForObject("http://localhost:10602/client/newUser", us, User.class);
		model.addAttribute("usuario", us);
		return "index";
	}
	
	@RequestMapping (value = "pagina-borrar-usuario", method = RequestMethod.GET)
	public String mostrarElFormularioBorrarUsuario(){
		return "ViewDeleteUsuario";
	}
	
	@RequestMapping (value = "pagina-delete-usuario", method = RequestMethod.POST)
	public String deleteUser(Model model, @RequestParam String userMail){
		User delUser = restTemplate.getForObject("http://localhost:10602/client/user?mail={mail}",
		User.class, userMail);
		if (delUser != null) {
		restTemplate.delete("http://localhost:10602/client/deleteUser?mail={mail}", userMail);
		}
		return "index";
	}
	
	@RequestMapping (value = "pagina-update-usuario", method = RequestMethod.GET)
	public String mostrarElFormularioUpdateUsuario(Model modelo){
		modelo.addAttribute("usuario", new User());
		return "ViewUpdateUsuario.html";
	}
	
	@RequestMapping (value = "pagina-search-usuario", method = RequestMethod.POST)
	public String searchUsuarios(Model model, @RequestParam String mail) {
		User us = restTemplate.getForObject("http://localhost:10602/client/user?mail={mail}",
		User.class, mail);
		model.addAttribute("usuario", us);
		return "viewUpdateUsuario";
	}
	
	@RequestMapping (value = "pagina-update-usuario", method = RequestMethod.POST)
	public String deleteUser(Model model, @ModelAttribute User us){
		restTemplate.put("http://localhost:10602/client/modifyUser", us, User.class);
		return "index";
	}
	
	//TRANSACTION
	
	@RequestMapping (value = "pagina-todas-transacciones", method = RequestMethod.GET)
	public String returnTodosTransacciones(Model model) {
		Transaction[] listaTrans = restTemplate.getForObject("http://localhost:10604/finance/transactions",
		Transaction[].class);
		model.addAttribute("transacciones", listaTrans);
		return "viewTodasTransacciones";
	}
}
