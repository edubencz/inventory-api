package br.com.inventory.interactions.report;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.inventory.dtos.report.ProductGrossProfitDTO;
import br.com.inventory.dtos.report.ProductStockBalanceDTO;
import br.com.inventory.entities.ProductType;
import br.com.inventory.repositories.MovementRepository;

@Service
public class ReportService {
	@Autowired
	private MovementRepository movementRepository;

	public List<ProductStockBalanceDTO> stockBalance(ProductType productType) {
		return movementRepository.getStockBalance(productType);
	}

	public ProductGrossProfitDTO grossProfit(String productCode) {
		return movementRepository.getGrossProfit(productCode);
	}
}
