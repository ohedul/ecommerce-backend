package bd.ohedul.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import bd.ohedul.erp.model.Buyer;

public interface BuyerRepository extends JpaRepository<Buyer, Long> {
	public Buyer findByEmail(String email);
}
