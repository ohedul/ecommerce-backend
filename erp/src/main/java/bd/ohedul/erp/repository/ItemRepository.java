package bd.ohedul.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import bd.ohedul.erp.model.Items;

public interface ItemRepository extends JpaRepository<Items, Long> {
	public Items findByImageName(String imageName);
}
