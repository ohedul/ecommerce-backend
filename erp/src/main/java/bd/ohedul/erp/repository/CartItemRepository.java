package bd.ohedul.erp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import bd.ohedul.erp.model.Cart;
import bd.ohedul.erp.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long>{
	public List<CartItem> findAllByCart(Cart cart);

}
