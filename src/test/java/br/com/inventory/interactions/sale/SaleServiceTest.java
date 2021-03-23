package br.com.inventory.interactions.sale;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.inventory.dtos.sale.SaleDTO;
import br.com.inventory.entities.Movement;
import br.com.inventory.entities.MovementType;
import br.com.inventory.entities.Product;
import br.com.inventory.exceptions.ProductDisabledException;
import br.com.inventory.exceptions.ProductNotFoundException;
import br.com.inventory.exceptions.ProductUnavailableException;
import br.com.inventory.repositories.MovementRepository;
import br.com.inventory.repositories.ProductRepository;

@ExtendWith(MockitoExtension.class)
class SaleServiceTest {
	@Mock
	MovementRepository movementRepository;
	@Mock
	ProductRepository productRepository;
	@InjectMocks
	SaleService saleService;
	@Captor
	private ArgumentCaptor<Movement> movementArgumentCaptor;
	@Captor
	private ArgumentCaptor<Product> productArgumentCaptor;

	@Test
	public void hasProductWhenSoldThenSuccess() {
		SaleDTO saleDTO = buildSaleDTO( "123", 5 );
		doReturn( Optional.of( buildProduct( saleDTO.getProductCode(), 10, Boolean.TRUE ) ) )
				.when( productRepository ).findByCode( saleDTO.getProductCode() );

		saleService.sell( saleDTO );

		verify( productRepository ).save( productArgumentCaptor.capture() );
		Product product = productArgumentCaptor.getValue();
		assertThat( product.getQuantity(), equalTo( 5 ) );

		verify( movementRepository ).save( movementArgumentCaptor.capture() );
		Movement movement = movementArgumentCaptor.getValue();
		assertThat( movement.getMovementType(), equalTo( MovementType.OUT ) );
		assertThat( movement.getQuantity(), equalTo( saleDTO.getQuantity() ) );
		assertThat( movement.getValue(), equalTo( saleDTO.getValue() ) );
		assertThat( movement.getEventAt(), equalTo( saleDTO.getSaleAt() ) );
		assertThat( movement.getProduct().getCode(), equalTo( saleDTO.getProductCode() ) );
		assertThat( movement.getGrossProfit(), equalTo( BigDecimal.valueOf( 250.0 ) ) );
	}

	@Test
	public void productIsDisabledWhenTrySellThenThrowProductDisabledException() {
		Assertions.assertThrows( ProductDisabledException.class, () -> {
			SaleDTO saleDTO = buildSaleDTO( "123", 5 );
			doReturn( Optional.of( buildProduct( saleDTO.getProductCode(), 10, Boolean.FALSE ) ) )
					.when( productRepository ).findByCode( saleDTO.getProductCode() );

			saleService.sell( saleDTO );
		} );
	}

	@Test
	public void quantityLessThanSaleWhenTrySellThenThrowProductUnavailableException() {
		Assertions.assertThrows( ProductUnavailableException.class, () -> {
			SaleDTO saleDTO = buildSaleDTO( "123", 5 );
			doReturn( Optional.of( buildProduct( saleDTO.getProductCode(), 3, Boolean.TRUE ) ) )
					.when( productRepository ).findByCode( saleDTO.getProductCode() );

			saleService.sell( saleDTO );
		} );
	}

	@Test
	public void productNotExistsWhenTrySellThenThrowProductNotFoundException() {
		Assertions.assertThrows( ProductNotFoundException.class, () -> {
			SaleDTO saleDTO = buildSaleDTO( "123", 5 );
			doReturn( Optional.empty() ).when( productRepository ).findByCode( saleDTO.getProductCode() );

			saleService.sell( saleDTO );
		} );
	}

	private Product buildProduct(final String code, final Integer quantity, final Boolean enabled) {
		return Product.builder()
				.code( code )
				.quantity( quantity )
				.enabled( enabled )
				.value( BigDecimal.valueOf( 50.00 ) )
				.build();
	}

	private SaleDTO buildSaleDTO(final String code, final Integer quantity) {
		return SaleDTO.builder()
				.productCode( code )
				.quantity( quantity )
				.value( BigDecimal.valueOf( 100.00 ) )
				.build();
	}

}

