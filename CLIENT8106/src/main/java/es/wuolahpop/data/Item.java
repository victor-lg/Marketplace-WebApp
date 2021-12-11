package es.wuolahpop.data;

import java.io.Serializable;
import javax.persistence.*;

public class Item implements Serializable {
	private static final long serialVersionUID = 1L;

	private String category;

	private String description;

	private byte[] photo;

	private float price;

	private String title;

	private User user;

	public Item() {
	}

	public Item(String category, String description, byte[] photo, float price,
			String title, User user) {
		super();
		this.category = category;
		this.description = description;
		this.photo = photo.clone();
		this.price = price;
		this.title = title;
		this.user = user;
	}


	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getPhoto() {
		return this.photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}


	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}