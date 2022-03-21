package bd.ohedul.erp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bd.ohedul.erp.model.Products;
import bd.ohedul.erp.repository.ProductRepository;

@Service
public class ProductService {

	private final ProductRepository repository;

	@Autowired
	public ProductService(ProductRepository repository) {
		super();
		this.repository = repository;
	}

	public List<Products> getAllProducts() {
		return repository.findAll();
	}

}
