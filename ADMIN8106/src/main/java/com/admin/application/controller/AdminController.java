package com.admin.application.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.admin.application.domains.Item;
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
		User user = restTemplate.postForObject("http://localhost:10602/client/newUser", us, User.class);
		System.out.println(user.getMail());
		model.addAttribute("usuario", user);
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
	public String modifyUser(Model model, @ModelAttribute User us){
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
	
	// CATALOGO
	
	@RequestMapping (value = "pagina-todos-items", method = RequestMethod.GET)
	public String returnTodosItems(Model model) {
		Item[] listaIt = restTemplate.getForObject("http://localhost:10603/catalogue/requestAll",
		Item[].class);
		model.addAttribute("items", listaIt);
		return "viewItems";
	}
	
	@RequestMapping (value="pagina-crear-item", method=RequestMethod.GET)
	public String mostrarElFormularioDelItem(Model modelo) {
		modelo.addAttribute("item", new Item());
		return "ViewCrearItem";
	}
	
	@RequestMapping (value = "pagina-post-item", method = RequestMethod.POST)
	public String saveItem(Model model, @ModelAttribute Item item, @RequestParam("file") MultipartFile file) {

        InputStream fileContent = null;
        byte[] imageBytes = null;
		try {
			fileContent = file.getInputStream();
			imageBytes = new byte[(int) file.getSize()];
			fileContent.read(imageBytes , 0, imageBytes.length);
			fileContent.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        String image = Base64.getEncoder().encodeToString(imageBytes);
		item.setPhoto(image);
		restTemplate.postForObject("http://localhost:10603/catalogue/insertion", item, Item.class);
		model.addAttribute("item", item);
		return "index";
	}
	
	@RequestMapping (value = "pagina-borrar-item", method = RequestMethod.GET)
	public String mostrarElFormularioBorrarItem(){
		return "ViewDeleteItem";
	}
	
	@RequestMapping (value = "pagina-delete-item", method = RequestMethod.POST)
	public String deleteItem(Model model, @RequestParam int itemId){
		Item delIt = restTemplate.getForObject("http://localhost:10603/catalogue/requestById?itemId={itemId}",
		Item.class, itemId);
		if (delIt != null) {
		restTemplate.delete("http://localhost:10603/catalogue/delete?itemId={itemId}", itemId);
		}
		return "index";
	}
	
	@RequestMapping (value = "pagina-update-item", method = RequestMethod.GET)
	public String mostrarElFormularioUpdateItem(Model modelo){
		modelo.addAttribute("item", new Item());
		return "ViewUpdateItem";
	}
	
	@RequestMapping (value = "pagina-search-item", method = RequestMethod.POST)
	public String searchItems(Model model, @RequestParam int itemId) {
		Item item = restTemplate.getForObject("http://localhost:10603/catalogue/requestById?itemId={itemId}",
		Item.class, itemId);
		System.out.println(item.getPhoto());
		model.addAttribute("item", item);
		return "viewUpdateItem";
	}
	
	@RequestMapping (value = "pagina-update-item", method = RequestMethod.POST)
	public String modifyItem(Model model, @ModelAttribute Item item, @RequestParam(name ="file", required=false) MultipartFile file) throws IOException{
		if (file.getBytes().length!=0) {
			InputStream fileContent = null;
			byte[] imageBytes = null;
			try {
				fileContent = file.getInputStream();
				imageBytes = new byte[(int) file.getSize()];
				fileContent.read(imageBytes , 0, imageBytes.length);
				fileContent.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			String image = Base64.getEncoder().encodeToString(imageBytes);
			item.setPhoto(image);
		}
		
		restTemplate.put("http://localhost:10603/catalogue/update", item, Item.class);
		return "index";
	}
}
