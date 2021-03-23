package br.com.inventory.dtos.product;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import br.com.inventory.entities.Product;
import br.com.inventory.entities.ProductType;

@Getter
@Builder
@AllArgsConstructor
public class ProductDTO {
	private final UUID id;
	private final ProductType productType;
	private final String code;
	private final String description;
	private final BigDecimal value;
	private final Integer quantity;
	private final Boolean enabled;

	public Product from() {
		return Product.builder()
				.id( getId() )
				.productType( getProductType() )
				.code( getCode() )
				.description( getDescription() )
				.value( getValue() )
				.quantity( getQuantity() )
				.enabled( getEnabled() )
				.build();
	}
}
