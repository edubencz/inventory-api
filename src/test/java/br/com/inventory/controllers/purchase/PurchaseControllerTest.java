package br.com.inventory.controllers.purchase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.inventory.Application;
import br.com.inventory.dtos.purchase.PurchaseDTO;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
class PurchaseControllerTest {
	private static final String DEFAULT_PATH = "/purchase";

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testPurchase() {
		final PurchaseDTO purchaseDTO = build();

		ResponseEntity<String> response = restTemplate.postForEntity( DEFAULT_PATH, purchaseDTO, String.class );

		assertThat( response.getStatusCode(), equalTo( HttpStatus.OK ) );
	}

	private PurchaseDTO build() {
		return PurchaseDTO.builder()
				.productCode( "0101016" )
				.value( BigDecimal.valueOf( 1500 ) )
				.quantity( 10 )
				.purchaseAt( LocalDateTime.now() )
				.build();
	}

}
