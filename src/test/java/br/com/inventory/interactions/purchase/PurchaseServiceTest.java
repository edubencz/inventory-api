package br.com.inventory.interactions.purchase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.inventory.dtos.purchase.PurchaseDTO;
import br.com.inventory.entities.Movement;
import br.com.inventory.entities.MovementType;
import br.com.inventory.entities.Product;
import br.com.inventory.entities.ProductType;
import br.com.inventory.exceptions.ProductNotFoundException;
import br.com.inventory.repositories.MovementRepository;
import br.com.inventory.repositories.ProductRepository;

@ExtendWith(MockitoExtension.class)
class PurchaseServiceTest {
	@Mock
	private MovementRepository movementRepository;
	@Mock
	private ProductRepository productRepository;
	@InjectMocks
	private PurchaseService purchaseService;
	@Captor
	private ArgumentCaptor<Movement> movementArgumentCaptor;
	@Captor
	private ArgumentCaptor<Product> productArgumentCaptor;

	@Test
	public void testPurchase() {
		final PurchaseDTO purchaseDTO = build();
		final Product product = buidProduct();
		doReturn( Optional.of( product ) ).when( productRepository ).findByCode( purchaseDTO.getProductCode() );

		purchaseService.purchase( purchaseDTO );

		verify( movementRepository ).save( movementArgumentCaptor.capture() );
		verify( productRepository ).save( productArgumentCaptor.capture() );
		assertThat( productArgumentCaptor.getValue().getQuantity(), equalTo( 11 ) );
		assertThat( productArgumentCaptor.getValue().getValue().doubleValue(), equalTo( 145.0 ) );
		assertThat( movementArgumentCaptor.getValue().getMovementType(), equalTo( MovementType.IN ) );
	}

	@Test
	public void productNotExistsWhenTryPurchaseThenThrowProductNotFoundException() {
		Assertions.assertThrows( ProductNotFoundException.class, () -> {
			final PurchaseDTO purchaseDTO = build();
			doReturn( Optional.empty() ).when( productRepository ).findByCode( purchaseDTO.getProductCode() );

			purchaseService.purchase( purchaseDTO );
		} );
	}

	private Product buidProduct() {
		return Product.builder()
				.id( UUID.randomUUID() )
				.code( "123" )
				.description( "Teste teste" )
				.productType( ProductType.HOME_APPLIANCES )
				.quantity( 1 )
				.value( BigDecimal.valueOf( 100 ) )
				.enabled( Boolean.TRUE )
				.build();
	}

	private PurchaseDTO build() {
		return PurchaseDTO.builder()
				.productCode( "123" )
				.value( BigDecimal.valueOf( 1500 ) )
				.quantity( 10 )
				.purchaseAt( LocalDateTime.now() )
				.build();
	}

}
