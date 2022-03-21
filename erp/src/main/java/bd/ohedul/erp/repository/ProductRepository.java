package bd.ohedul.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import bd.ohedul.erp.model.Products;

public interface ProductRepository extends JpaRepository<Products, Integer> {

	public Products findByName(String name);

}
