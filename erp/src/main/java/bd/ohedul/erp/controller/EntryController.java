package bd.ohedul.erp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bd.ohedul.erp.dto.BuyerDto;
import bd.ohedul.erp.dto.User;
import bd.ohedul.erp.model.Buyer;
import bd.ohedul.erp.service.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/")
public class EntryController {
	private final UserService service;
	
	@Autowired
	public EntryController(UserService service) {
		this.service = service;
	}

	@PostMapping(value = "/register")
	public ResponseEntity<Buyer> saveBuyer(@RequestBody BuyerDto user) {
		try {
			Buyer buyer = service.saveUser(user);
			if (buyer != null) {
				return ResponseEntity.ok().body(buyer);
			}
			return ResponseEntity.badRequest().body(new Buyer());
		}catch(Exception e) {
			return ResponseEntity.badRequest().body(new Buyer());
		}
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<Buyer> doLogin(@RequestBody User user) {
		try {
			Buyer buyer = service.getUser(user);
			if (buyer != null) {
				return ResponseEntity.ok().body(buyer);
			}
			return ResponseEntity.badRequest().body(new Buyer());
		}catch(Exception e) {
			return ResponseEntity.badRequest().body(new Buyer());
		}
		
	}
	
	
	@GetMapping
	public List<String> getInitial() {
		return new ArrayList<String>();

	}

}
