package br.com.inventory.interactions.sale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.inventory.dtos.sale.SaleDTO;
import br.com.inventory.entities.Product;
import br.com.inventory.exceptions.ProductDisabledException;
import br.com.inventory.exceptions.ProductNotFoundException;
import br.com.inventory.exceptions.ProductUnavailableException;
import br.com.inventory.repositories.MovementRepository;
import br.com.inventory.repositories.ProductRepository;

@Service
public class SaleService {
	@Autowired
	private MovementRepository movementRepository;
	@Autowired
	private ProductRepository productRepository;

	public void sell(SaleDTO saleDTO) {
		final Product product = findProductByCode( saleDTO.getProductCode() );
		checkProductEnabled( product.getEnabled(), saleDTO.getProductCode() );
		checkQuantity( product.getQuantity(), saleDTO.getQuantity(), saleDTO.getProductCode() );
		product.setQuantity( product.getQuantity() - saleDTO.getQuantity() );
		movementRepository.save( saleDTO.from( product ) );
		productRepository.save( product );
	}

	private void checkProductEnabled(final Boolean enabled, final String code) {
		if (!Boolean.TRUE.equals( enabled ))
			throw new ProductDisabledException( code );
	}

	private void checkQuantity(final Integer availableQuantity, final Integer soldQuantity, final String code) {
		if (soldQuantity > availableQuantity)
			throw new ProductUnavailableException( code );
	}

	private Product findProductByCode(final String code) {
		return productRepository.findByCode( code )
				.orElseThrow( () -> new ProductNotFoundException( code ) );
	}

}
