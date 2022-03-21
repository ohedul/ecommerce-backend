package bd.ohedul.erp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnore;

import bd.ohedul.erp.dto.OrderDto;
import bd.ohedul.erp.service.CartService;

@CrossOrigin("*")
@RestController
@RequestMapping("/cart")
public class CartController {
	private final CartService service;

	@Autowired
	public CartController(CartService service) {
		super();
		this.service = service;
	}

	@PostMapping(value = "/add/{userId}")
	@JsonIgnore
	public ResponseEntity<Long> addCart(@RequestBody List<int[]> cartDto, @PathVariable("userId") Long userId) {
		Long value = service.addCart(cartDto, userId);
		return ResponseEntity.ok(value);
	}

	@GetMapping
	public List<OrderDto> getAllOrders() {
		return service.getAllOrders();
	}

	@GetMapping(value = "/customer/{buyerId}")
	public List<OrderDto> getAllOrdersFromCustomers(@PathVariable("buyerId") Long buyerId) {
		return service.getAllOrdersByCustomer(buyerId);
	}

	@GetMapping(value = "/accept/{orderId}")
	public void acceptOrder(@PathVariable("orderId") Long orderId) {
		service.accept(orderId);
	}

}
