package br.com.inventory.interactions.purchase;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.inventory.dtos.purchase.PurchaseDTO;
import br.com.inventory.entities.Product;
import br.com.inventory.exceptions.ProductNotFoundException;
import br.com.inventory.repositories.MovementRepository;
import br.com.inventory.repositories.ProductRepository;

@Service
public class PurchaseService {
	@Autowired
	private MovementRepository movementRepository;
	@Autowired
	private ProductRepository productRepository;

	public void purchase(PurchaseDTO purchaseDTO) {
		Product product = getProductByCode( purchaseDTO.getProductCode() );
		product.setValue( calculateValue( product, purchaseDTO ) );
		product.setQuantity( product.getQuantity() + purchaseDTO.getQuantity() );

		movementRepository.save( purchaseDTO.from( product ) );
		productRepository.save( product );
	}

	private BigDecimal calculateValue(final Product product, final PurchaseDTO purchaseDTO) {
		final BigDecimal valueTotal = product.getValue().multiply( BigDecimal.valueOf( product.getQuantity() ) );
		final Integer quantity = product.getQuantity() + purchaseDTO.getQuantity();
		return valueTotal.add( purchaseDTO.getValue() )
				.divide( BigDecimal.valueOf( quantity ), RoundingMode.HALF_UP );
	}

	private Product getProductByCode(final String code) {
		return productRepository.findByCode( code )
				.orElseThrow( () -> new ProductNotFoundException( code ) );
	}

}
