package br.com.inventory.controllers.report;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.inventory.Application;
import br.com.inventory.dtos.report.ProductGrossProfitDTO;
import br.com.inventory.dtos.report.ProductStockBalanceDTO;
import br.com.inventory.entities.ProductType;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
class ReportControllerTest {

	private static final String DEFAULT_PATH = "/report";

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testStockBalanceReport() {
		final ResponseEntity<ProductStockBalanceDTO[]> response = restTemplate
				.getForEntity( DEFAULT_PATH + "/stock-balance/" + ProductType.FURNITURE,
						ProductStockBalanceDTO[].class );

		assertThat( response.getStatusCode(), equalTo( HttpStatus.OK ) );
		final ProductStockBalanceDTO productStockBalanceDTO = response.getBody()[0];
		assertThat( productStockBalanceDTO.getCode(), equalTo( "0101014" ) );
		assertThat( productStockBalanceDTO.getDescription(), equalTo( "Cadeira" ) );
		assertThat( productStockBalanceDTO.getBalanceAvailable(), equalTo( 30 ) );
		assertThat( productStockBalanceDTO.getOutputQuantity(), equalTo( 12L ) );
	}

	@Test
	public void testGrossProfit() {
		final ResponseEntity<ProductGrossProfitDTO> response = restTemplate
				.getForEntity( DEFAULT_PATH + "/gross-profit/0101014", ProductGrossProfitDTO.class );

		assertThat( response.getStatusCode(), equalTo( HttpStatus.OK ) );
		final ProductGrossProfitDTO productGrossProfitDTO = response.getBody();
		assertThat( productGrossProfitDTO.getCode(), equalTo( "0101014" ) );
		assertThat( productGrossProfitDTO.getDescription(), equalTo( "Cadeira" ) );
		assertThat( productGrossProfitDTO.getOutputQuantity(), equalTo( 12L ) );
		assertThat( productGrossProfitDTO.getGrossProfit().doubleValue(), equalTo( 270.0 ) );
	}
}
