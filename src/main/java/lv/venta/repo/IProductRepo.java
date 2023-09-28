package lv.venta.repo;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import lv.venta.models.Product;

public interface IProductRepo extends CrudRepository<Product, Integer> {
	//SELECT * FROM product_table WHERE price < var;
	ArrayList<Product> findByPriceLessThan(float var);
	
	//atlasa pēc nosaukuma
	//SELECT * FROM product_table WHERE title='<var>';
	ArrayList<Product> findByTitle(String var);
	
	//TODO atlase tos produktus, kuru daudzums ir lielāks par 10, bet cena mazaka par 4 eur
	//SELECT * FROM product_table WHERE quantity > varQ AND price < varP;0
	ArrayList<Product> findByQuantityGreaterThanAndPriceLessThan(int varQ, float varP);

	//SELECT * FROM product table WHERE quantity < "<quantity>"
	ArrayList<Product> findByQuantityLessThan(int quantity);

	//@Query(nativeQuery = countBy)
	//ArrayList<Product> funcNameDoesNotMatter();
}
