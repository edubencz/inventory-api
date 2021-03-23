package br.com.inventory.entities;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import br.com.inventory.dtos.product.ProductDTO;

class ProductTest {

	@Test
	public void grantConvertToDTO() {
		final Product product = buidProduct();

		final ProductDTO productDTO = product.from();

		assertThat( productDTO.getId(), equalTo( product.getId() ) );
		assertThat( productDTO.getCode(), equalTo( product.getCode() ) );
		assertThat( productDTO.getDescription(), equalTo( product.getDescription() ) );
		assertThat( productDTO.getProductType(), equalTo( product.getProductType() ) );
		assertThat( productDTO.getQuantity(), equalTo( product.getQuantity() ) );
		assertThat( productDTO.getValue(), equalTo( product.getValue() ) );
		assertThat( productDTO.getEnabled(), equalTo( product.getEnabled() ) );
	}

	private Product buidProduct() {
		return Product.builder()
				.id( UUID.randomUUID() )
				.code( "123" )
				.description( "Teste teste" )
				.productType( ProductType.HOME_APPLIANCES )
				.quantity( 1 )
				.value( BigDecimal.valueOf( 550 ) )
				.enabled( Boolean.TRUE )
				.build();
	}

}
