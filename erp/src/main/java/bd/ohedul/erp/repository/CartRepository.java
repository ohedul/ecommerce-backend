package bd.ohedul.erp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import bd.ohedul.erp.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
	public List<Cart> findByBuyerUserId(Long userId);

}
