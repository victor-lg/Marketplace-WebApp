package com.catalogue.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.catalogue.domains.Item;

public interface ItemRepository extends CrudRepository<Item, String> {
	
	public Item findByItemId(String item_id);
	
	public List<Item> findByCategory(String category);

	public List<Item> findByTitle(String title);
	
	public List<Item> findByDescription(String description);
	
	public List<Item> findByUserCity(String city);
	
	public List<Item> findByUserName(String name);
	
}
