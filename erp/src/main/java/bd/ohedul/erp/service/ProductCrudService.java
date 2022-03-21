package bd.ohedul.erp.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import antlr.StringUtils;
import bd.ohedul.erp.model.ProductMultipart;
import bd.ohedul.erp.model.Products;
import bd.ohedul.erp.repository.ProductRepository;

@Service
public class ProductCrudService {
	
	private final ProductRepository repository;
	
	
	@Autowired
	public ProductCrudService(ProductRepository repository) {
		this.repository = repository;
	}
	
	public Products saveProducts(ProductMultipart formData) {
		Products newProduct = new Products();
		newProduct.setName(formData.getName());
		newProduct.setDescription(formData.getDescription());
		newProduct.setPrice(formData.getPrice());
		repository.save(newProduct);
		if (newProduct.getId() != null) {
			MultipartFile file = formData.getFile()[0];
			String extension = file.getContentType().substring(6);
			String imageName = "ITEM-2022-" + String.valueOf(newProduct.getId())+ "." + extension;
			System.out.println(extension);
			System.out.println(imageName);
			try {
				String fileLocation = new File("src\\main\\resources\\static\\image").getAbsolutePath()+ "\\" +imageName;
				byte[] bytes = file.getBytes();
				Path path = Paths.get(fileLocation);
				Files.write(path, bytes);
				newProduct.setImageName(imageName);
				repository.saveAndFlush(newProduct);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
	public List<Products> getAllProducts(){
		return repository.findAll();
	}
	
	
	
	

}
