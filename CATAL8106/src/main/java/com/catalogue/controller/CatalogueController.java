
   
package com.catalogue.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.catalogue.checks.ValidacionCategoria;
import com.catalogue.domains.Item;
import com.catalogue.domains.User;
import com.catalogue.repositories.ItemRepository;

@RestController
@CrossOrigin
@EnableAutoConfiguration
@RequestMapping("/catalogue")

public class CatalogueController {

	@Autowired
	ItemRepository daoItem;

	@PostMapping(value = "/insertion", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> saveItem(@RequestBody @Validated Item item) {
		try {

			item.setState("Disponible");
			ValidacionCategoria _validacionCategoria = new ValidacionCategoria();

			if (_validacionCategoria.validarCategoria(item.getCategory())) {

				if (item.getDescription().length() <= 500) {
					daoItem.save(item);
					System.out.println(item);
					return new ResponseEntity<>(item, HttpStatus.OK);
				} else {
					return new ResponseEntity<>(
							"La descripción no debe sobrepasar los 500 caracteres: " + item.getDescription().length(),
							HttpStatus.BAD_REQUEST);
				}

			} else {
				return new ResponseEntity<>("Categoría no válida: " + item.getCategory(), HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/requestById" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> getItemById(@RequestParam(value = "itemId", required = true) String item_id) {
		try {
			int itemId=Integer.parseInt(item_id);
			Item it = daoItem.findByItemId(itemId);
			return new ResponseEntity<>(it, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/requestByCategory", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
    public ResponseEntity<?> getItemsByCategory(@RequestParam(value = "category", required = true) String category) {
		try {
			List<Item> itemsList = daoItem.findByCategory(category);
			return new ResponseEntity<>(daoItem.findByCategory(category), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	/*
	@RequestMapping("/requestByCategory")
	public ResponseEntity<?> getItemsByCategory(@RequestBody Item item) {
		try {
			return new ResponseEntity<>(daoItem.findByCategory(item.getCategory()), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	*/
	
	@RequestMapping(value = "/requestByDescription", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getItemsByDescription(@RequestParam(value = "description", required = true) String description) {
		try {
			return new ResponseEntity<>(daoItem.findByDescription(description), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	/*
	@RequestMapping("/requestByDescription")
	public ResponseEntity<?> getItemsByDescription(@RequestBody Item item) {
		try {
			return new ResponseEntity<>(daoItem.findByDescription(item.getDescription()), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	*/
	
	@RequestMapping(value = "/requestByTitle", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getItemsByTitle(@RequestParam(value = "title", required = true) String title) {
		try {
			return new ResponseEntity<>(daoItem.findByTitle(title), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	/*
	@RequestMapping("/requestByTitle")
	public ResponseEntity<?> getItemsByTitle(@RequestBody Item item) {
		try {
			return new ResponseEntity<>(daoItem.findByTitle(item.getTitle()), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	*/
	/*
	@RequestMapping(value = "/requestByCity", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getItemsByCity(@RequestParam(value = "city", required = true) String city) {
		try {
			return new ResponseEntity<>(daoItem.findByUserCity(city), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	*/
	/*
	@RequestMapping("/requestByCity")
	public ResponseEntity<?> getItemsByCity(@RequestBody Item item) {
		try {
			return new ResponseEntity<>(daoItem.findByUserCity(item.getUser().getCity()), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	*/
	/*
	@RequestMapping(value = "/requestByVendor", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getItemsByVendor(@RequestParam(value = "vendor", required = true) String vendor) {
		try {
			return new ResponseEntity<>(daoItem.findByUserName(vendor), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	*/
	/*
	@RequestMapping("/requestByVendor")
	public ResponseEntity<?> getItemsByVendor(@RequestBody Item item) {
		try {
			return new ResponseEntity<>(daoItem.findByUserName(item.getUser().getName()), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	*/
	
	@RequestMapping(value = "/requestAll", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> getAllItems() {
		try {
			Iterable<Item> listItems = daoItem.findAll();
			return new ResponseEntity<>(daoItem.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping("/delete")
	public ResponseEntity<?> deleteItemById(@RequestBody Item item) {
		try {
			Item it = daoItem.findById(item.getItemId()).orElse(null);
			if (it != null) {
				daoItem.delete(it);
			}
			return new ResponseEntity<>(it, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping("/update")
	public ResponseEntity<?> updateItemById(@RequestBody Item item) {
		try {
			Item it = daoItem.findById(item.getItemId()).orElse(null);

			if (item.getCategory() != null) {
				ValidacionCategoria _validacionCategoria = new ValidacionCategoria();
				if (_validacionCategoria.validarCategoria(item.getCategory())) {
					it.setCategory(item.getCategory());
				} else {
					return new ResponseEntity<>("Categoría no válida: " + item.getCategory(), HttpStatus.BAD_REQUEST);
				}
			}

			if (item.getTitle() != null) {
				it.setTitle(item.getTitle());
			}

			if (item.getDescription() != null) {
				if (item.getDescription().length() <= 500) {
					it.setDescription(item.getDescription());
				} else {
					return new ResponseEntity<>(
							"La descripción no debe sobrepasar los 500 caracteres: " + item.getDescription().length(),
							HttpStatus.BAD_REQUEST);
				}
			}

			if (item.getPhoto() != null) {
				it.setPhoto(item.getPhoto());
			}

			if (item.getPrice() != 0) {
				it.setPrice(item.getPrice());
			}
			/*
			 * if (item.getState() != null) { it.setState(item.getState()); }
			 */
			daoItem.save(it);
			return new ResponseEntity<>(it, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping("/changeState")
	public ResponseEntity<?> updateItemStateById(@RequestBody Item item) {
		try {
			Item it = daoItem.findById(item.getItemId()).orElse(null);
			if (item.getState() != null && (item.getState().equals("Reservado") || item.getState().equals("Vendido")
					|| item.getState().equals("Disponible"))) {
				it.setState(item.getState());
			} else {
				return new ResponseEntity<>("No se ha especificado un estado válido", HttpStatus.BAD_REQUEST);
			}
			daoItem.save(it);
			return new ResponseEntity<>(it, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
