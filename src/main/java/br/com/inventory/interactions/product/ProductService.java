package br.com.inventory.interactions.product;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.inventory.dtos.product.ProductDTO;
import br.com.inventory.entities.Product;
import br.com.inventory.exceptions.ProductNotAllowDeleteException;
import br.com.inventory.exceptions.ProductNotFoundException;
import br.com.inventory.repositories.MovementRepository;
import br.com.inventory.repositories.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private MovementRepository movementRepository;

	public ProductDTO findById(final UUID id) {
		return getProduct( id ).from();
	}

	public List<ProductDTO> findAll() {
		return productRepository.findAll().stream().map( Product::from ).collect( Collectors.toList() );
	}

	public void save(final ProductDTO productDTO) {
		productRepository.save( productDTO.from() );
	}

	public void update(final UUID id, final ProductDTO productDTO) {
		final Product product = getProduct( id );
		product.setCode( productDTO.getCode() );
		product.setDescription( productDTO.getDescription() );
		product.setValue( productDTO.getValue() );
		product.setQuantity( productDTO.getQuantity() );
		product.setEnabled( Boolean.TRUE );

		productRepository.save( product );
	}

	public void delete(final UUID uuid) {
		existsProduct( uuid );
		checkHasProductMovement( uuid );
		productRepository.deleteById( uuid );
	}

	private void existsProduct(final UUID uuid) {
		if (!productRepository.existsById( uuid ))
			throw new ProductNotFoundException( uuid );
	}

	public void checkHasProductMovement(final UUID uuid) {
		if (movementRepository.existMovement( uuid ))
			throw new ProductNotAllowDeleteException( uuid );
	}

	public void disable(final UUID id) {
		final Product product = getProduct( id );
		product.setEnabled( Boolean.FALSE );
		productRepository.save( product );
	}

	public ProductDTO findByCode(final String code) {
		return productRepository.findByCode( code )
				.orElseThrow( () -> new ProductNotFoundException( code ) ).from();
	}

	private Product getProduct(final UUID id) {
		return productRepository.findById( id )
				.orElseThrow( () -> new ProductNotFoundException( id ) );
	}

}
