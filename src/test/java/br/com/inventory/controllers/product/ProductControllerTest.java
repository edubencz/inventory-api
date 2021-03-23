package br.com.inventory.controllers.product;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.inventory.Application;
import br.com.inventory.dtos.product.ProductDTO;
import br.com.inventory.dtos.rest.RestError;
import br.com.inventory.entities.ProductType;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
class ProductControllerTest {
	private static final String DEFAULT_PATH = "/product/";

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testFindAllWithoutPaginated() {
		final ResponseEntity<ProductDTO[]> response = restTemplate
				.getForEntity( DEFAULT_PATH + "all", ProductDTO[].class );

		assertThat( response.getStatusCode(), equalTo( HttpStatus.OK ) );
		assertThat( response.getBody(), notNullValue() );
	}

	@Test
	public void testGetByCode() {
		final ResponseEntity<ProductDTO> response = restTemplate
				.getForEntity( DEFAULT_PATH + "?code=0101014", ProductDTO.class );

		assertThat( response.getStatusCode(), equalTo( HttpStatus.OK ) );
		assertThat( response.getBody(), notNullValue() );
	}

	@Test
	public void testGetById() {
		final ResponseEntity<ProductDTO> response = restTemplate
				.getForEntity( DEFAULT_PATH + "1fd0c5a4-87d6-41f9-a010-feeb2d316859", ProductDTO.class );

		assertThat( response.getStatusCode(), equalTo( HttpStatus.OK ) );
		assertThat( response.getBody(), notNullValue() );
	}

	@Test
	public void testSave() {
		final ProductDTO productDTO = buildProductDTO( UUID.randomUUID(), "654321" );

		final ResponseEntity response = restTemplate.postForEntity( DEFAULT_PATH, productDTO, String.class );

		assertThat( response.getStatusCode(), equalTo( HttpStatus.OK ) );
	}

	@Test
	public void testUpdate() {
		final ProductDTO productDTO = buildProductDTO( null, "123456" );
		final HttpEntity request = new HttpEntity( productDTO );

		ResponseEntity<String> response = restTemplate
				.exchange( DEFAULT_PATH + "1fd0c5a4-87d6-41f9-a010-feeb2d316860", HttpMethod.PUT, request,
						String.class );

		assertThat( response.getStatusCode(), equalTo( HttpStatus.OK ) );
	}

	@Test
	public void testDelete() {
		ResponseEntity<String> response = restTemplate
				.exchange( DEFAULT_PATH + "1fd0c5a4-87d6-41f9-a010-feeb2d316862", HttpMethod.DELETE, null,
						String.class );

		assertThat( response.getStatusCode(), equalTo( HttpStatus.OK ) );
	}

	@Test
	public void testDisable() {
		ResponseEntity<String> response = restTemplate
				.exchange( DEFAULT_PATH + "disable/" + "1fd0c5a4-87d6-41f9-a010-feeb2d316860", HttpMethod.DELETE, null,
						String.class );

		assertThat( response.getStatusCode(), equalTo( HttpStatus.OK ) );
	}

	@Test
	public void productNotExistsWhenTryUpdateThenThrowProductNotFoundException() {
		final String uuidAsString = "1fd0c5a4-87d6-41f9-a010-feeb2d316889";
		final ProductDTO productDTO = buildProductDTO( null, "123456" );
		final HttpEntity request = new HttpEntity( productDTO );

		ResponseEntity<RestError> response = restTemplate
				.exchange( DEFAULT_PATH + uuidAsString, HttpMethod.PUT, request,
						RestError.class );

		assertThat( response.getStatusCode(), equalTo( HttpStatus.NOT_FOUND ) );
		assertThat( response.getBody().getTitle(), equalTo( "Registro não localizado" ) );
		assertThat( response.getBody().getDescription(),
				equalTo( "Não foi possível localizar Produto com o id " + uuidAsString ) );
	}

	@Test
	public void productNotExistsWhenTryDeleteThenThrowProductNotFoundException() {
		final String uuidAsString = "1fd0c5a4-87d6-41f9-a010-feeb2d316889";

		ResponseEntity<RestError> response = restTemplate
				.exchange( DEFAULT_PATH + uuidAsString, HttpMethod.DELETE, null,
						RestError.class );

		assertThat( response.getStatusCode(), equalTo( HttpStatus.NOT_FOUND ) );
		assertThat( response.getBody().getTitle(), equalTo( "Registro não localizado" ) );
		assertThat( response.getBody().getDescription(),
				equalTo( "Não foi possível localizar Produto com o id " + uuidAsString ) );
	}

	@Test
	public void productExistsWitMovementWhenTryDeleteThenThrowProductNotAllowDeleteException() {
		final String uuidAsString = "1fd0c5a4-87d6-41f9-a010-feeb2d316859";

		ResponseEntity<RestError> response = restTemplate
				.exchange( DEFAULT_PATH + uuidAsString, HttpMethod.DELETE, null,
						RestError.class );

		assertThat( response.getStatusCode(), equalTo( HttpStatus.BAD_REQUEST ) );
		assertThat( response.getBody().getTitle(), equalTo( "Não foi possível processar a requisição" ) );
		assertThat( response.getBody().getDescription(),
				equalTo( "Produto com o id " + uuidAsString + " não pode ser excluído." ) );
	}

	private ProductDTO buildProductDTO(final UUID uuid, final String code) {
		return ProductDTO.builder()
				.id( uuid )
				.code( code )
				.description( "Produto de Teste" )
				.productType( ProductType.ELECTRONIC )
				.quantity( 10 )
				.value( BigDecimal.valueOf( 100 ) )
				.enabled( Boolean.TRUE )
				.build();
	}
}
