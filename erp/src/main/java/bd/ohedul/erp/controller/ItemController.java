package bd.ohedul.erp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bd.ohedul.erp.dto.ProductMultipart;
import bd.ohedul.erp.model.Items;
import bd.ohedul.erp.service.ItemService;

@CrossOrigin("*")
@RestController
@RequestMapping("/items")
public class ItemController {

	private final ItemService service;

	@Autowired
	public ItemController(ItemService service) {
		super();
		this.service = service;
	}

	@PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<List<Items>> uploadFile(ProductMultipart file) {
		service.saveItem(file);
		return ResponseEntity.ok(service.getAllItems());

	}

	@GetMapping
	public List<Items> getAllItems() {
		return service.getAllItems();

	}

	@GetMapping(value = "/delete/{itemId}")
	public List<Items> deleteItem(@PathVariable("itemId") Long itemId) {
		return service.deleteItems(itemId);

	}

}
