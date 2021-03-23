package br.com.inventory.controllers.sale;

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
import br.com.inventory.dtos.rest.RestError;
import br.com.inventory.dtos.sale.SaleDTO;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
class SaleControllerTest {

	private static final String DEFAULT_PATH = "/sale";

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void hasProductWhenSoldThenSuccess() {
		final SaleDTO saleDTO = buildSaleDTO( "0101011", 1 );

		final ResponseEntity response = restTemplate.postForEntity( DEFAULT_PATH, saleDTO, String.class );

		assertThat( response.getStatusCodeValue(), equalTo( HttpStatus.OK.value() ) );
	}

	@Test
	public void productIsDisabledWhenTrySellThenThrowProductDisabledException() {
		final SaleDTO saleDTO = buildSaleDTO( "0101012", 1 );

		final ResponseEntity<RestError> response = restTemplate.postForEntity( DEFAULT_PATH, saleDTO, RestError.class );

		assertThat( response.getStatusCodeValue(), equalTo( HttpStatus.BAD_REQUEST.value() ) );
	}

	@Test
	public void quantityLessThanSaleWhenTrySellThenThrowProductUnavailableException() {
		final SaleDTO saleDTO = buildSaleDTO( "0101011", 100 );

		final ResponseEntity<RestError> response = restTemplate.postForEntity( DEFAULT_PATH, saleDTO, RestError.class );

		assertThat( response.getStatusCodeValue(), equalTo( HttpStatus.BAD_REQUEST.value() ) );
	}

	@Test
	public void productNotExistsWhenTrySellThenThrowProductNotFoundException() {
		final SaleDTO saleDTO = buildSaleDTO( "123", 1 );

		final ResponseEntity<RestError> response = restTemplate.postForEntity( DEFAULT_PATH, saleDTO, RestError.class );

		assertThat( response.getStatusCodeValue(), equalTo( HttpStatus.NOT_FOUND.value() ) );
	}

	private SaleDTO buildSaleDTO(final String code, final Integer quantity) {
		return SaleDTO.builder()
				.productCode( code )
				.value( BigDecimal.valueOf( 70.0 ) )
				.quantity( quantity )
				.saleAt( LocalDateTime.now() )
				.build();
	}

}
