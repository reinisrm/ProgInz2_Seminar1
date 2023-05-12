package lv.venta.models;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Table(name = "product_table")//DB pusē izveidosies tabula
@Entity
public class Product {

	@Column(name = "Id")//DB puse būs kolonna "id" un būs kā auto increment PK
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "Title")//DB puse būs kolonna title
	@NotNull
	@Pattern(regexp = "[A-ZĒŪĪĻĶŠĀŽČŅ]{1}[a-zēūīļķšāžčņ\\ ]+", message = "Pirmajam burtam jābūt lielajam")
	@Size(min = 3, max = 30, message = "Jabūt vismaz 3 un ne vairāk kā 30 simboliem")
	private String title;
	
	@Column(name = "Price")//DB puse bus kolonna price
	@Min(value = 0)
	@Max(value = 10000)
	private float price;
	
	@Column(name = "Description")
	@NotNull
	@Pattern(regexp = "[A-ZĒŪĪĻĶŠĀŽČŅ]{1}[a-zēūīļķšāžčņ\\ ]+")
	@Size(min = 3, max = 100)
	private String description;
	
	@Column(name = "Quantity")
	@Min(value = 0)
	@Max(value = 1000)
	private int quantity;
	

	
	public int getId() {
		return id;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Product(String title, float price, String description, int quantity) {
		this.title = title;
		this.price = price;
		this.description = description;
		this.quantity = quantity;
	}

	public Product() {
	}

}
