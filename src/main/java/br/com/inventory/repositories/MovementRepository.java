package br.com.inventory.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.inventory.dtos.report.ProductGrossProfitDTO;
import br.com.inventory.dtos.report.ProductStockBalanceDTO;
import br.com.inventory.entities.Movement;
import br.com.inventory.entities.Product;
import br.com.inventory.entities.ProductType;

@Repository
public interface MovementRepository extends JpaRepository<Movement, UUID> {
	@Query("SELECT CASE WHEN COUNT(m.id) > 0 THEN true ELSE false END FROM Movement m "
			+ "INNER JOIN Product p ON p = m.product "
			+ "WHERE p.id = :uuid")
	boolean existMovement(final UUID uuid);

	@Query("SELECT new br.com.inventory.dtos.report.ProductStockBalanceDTO(p.code, p.description, "
			+ "SUM(m.quantity), "
			+ "p.quantity) "
			+ "FROM Product p "
			+ "INNER JOIN Movement m ON p = m.product "
			+ "WHERE p.productType = :productType "
			+ "AND m.movementType = 'OUT' "
			+ "GROUP BY p.code, p.description, p.quantity")
	List<ProductStockBalanceDTO> getStockBalance(ProductType productType);

	@Query("SELECT new br.com.inventory.dtos.report.ProductGrossProfitDTO(p.code, p.description, "
			+ "SUM(m.quantity), "
			+ "SUM(m.grossProfit)) "
			+ "FROM Product p "
			+ "INNER JOIN Movement m ON p = m.product "
			+ "WHERE p.code = :productCode "
			+ "AND m.movementType = 'OUT' "
			+ "GROUP BY p.code, p.description, p.quantity")
	ProductGrossProfitDTO getGrossProfit(String productCode);
}
