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

import bd.ohedul.erp.dto.ProductMultipart;
import bd.ohedul.erp.model.Items;
import bd.ohedul.erp.model.Products;
import bd.ohedul.erp.repository.ItemRepository;

@Service
public class ItemService {
	private final ItemRepository repository;
	
	@Autowired
	public ItemService(ItemRepository repository) {
		super();
		this.repository = repository;
	}
	
	public Products saveItem(ProductMultipart formData) {
		Items item = new Items();
		item.setName(formData.getName());
		item.setDescription(formData.getDescription());
		item.setPrice(formData.getPrice());
		repository.save(item);
		if (item.getItemId() != null) {
			MultipartFile file = formData.getFile()[0];
			String extension = file.getContentType().substring(6);
			String imageName = "MENUITEM-2019-" + String.valueOf(item.getItemId())+ "." + extension;
			try {
				String fileLocation = new File("src\\main\\resources\\static\\image").getAbsolutePath()+ "\\" +imageName;
				byte[] bytes = file.getBytes();
				Path path = Paths.get(fileLocation);
				Files.write(path, bytes);
				item.setImageName(imageName);
				repository.saveAndFlush(item);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
	public List<Items> getAllItems(){
		return repository.findAll();
	}
	
	public List<Items> deleteItems(Long itemId){
		repository.deleteById(itemId);
		return repository.findAll();
	}
	
	

}
