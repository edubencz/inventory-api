package br.com.inventory.dtos.sale;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import br.com.inventory.entities.Movement;
import br.com.inventory.entities.MovementType;
import br.com.inventory.entities.Product;
import br.com.inventory.entities.ProductType;

class SaleDTOTest {

	@Test
	public void grantConvertToEntity() {
		final SaleDTO saleDTO = build();
		final Product product = buidProduct();

		Movement movement = saleDTO.from( product );

		assertThat( movement.getQuantity(), equalTo( saleDTO.getQuantity() ) );
		assertThat( movement.getValue(), equalTo( saleDTO.getValue() ) );
		assertThat( movement.getGrossProfit(), equalTo( BigDecimal.valueOf( 200 ) ) );
		assertThat( movement.getEventAt(), equalTo( saleDTO.getSaleAt() ) );
		assertThat( movement.getMovementType(), equalTo( MovementType.OUT ) );
		assertThat( movement.getProduct().getId(), equalTo( product.getId() ) );
		assertThat( movement.getProduct().getCode(), equalTo( saleDTO.getProductCode() ) );
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

	private SaleDTO build() {
		return SaleDTO.builder()
				.productCode( "123" )
				.quantity( 1 )
				.value( BigDecimal.valueOf( 750 ) )
				.saleAt( LocalDateTime.now() )
				.build();
	}

}
