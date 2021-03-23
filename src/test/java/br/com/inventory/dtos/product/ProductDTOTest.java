package br.com.inventory.dtos.product;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import br.com.inventory.entities.Product;
import br.com.inventory.entities.ProductType;

class ProductDTOTest {

	private static final UUID RANDOM_UUID = UUID.randomUUID();

	@Test
	public void grantConvertToEntity() {
		final ProductDTO productDTO = build();

		final Product product = productDTO.from();

		assertThat( product.getId(), equalTo( productDTO.getId() ) );
		assertThat( product.getCode(), equalTo( productDTO.getCode() ) );
		assertThat( product.getDescription(), equalTo( productDTO.getDescription() ) );
		assertThat( product.getProductType(), equalTo( productDTO.getProductType() ) );
		assertThat( product.getQuantity(), equalTo( productDTO.getQuantity() ) );
		assertThat( product.getValue(), equalTo( productDTO.getValue() ) );
		assertThat( product.getEnabled(), equalTo( productDTO.getEnabled() ) );
	}

	private ProductDTO build() {
		return ProductDTO.builder()
				.id( RANDOM_UUID )
				.code( "123" )
				.description( "Produto de Teste" )
				.productType( ProductType.ELECTRONIC )
				.quantity( 10 )
				.value( BigDecimal.valueOf( 100 ) )
				.enabled( Boolean.TRUE )
				.build();
	}

}
