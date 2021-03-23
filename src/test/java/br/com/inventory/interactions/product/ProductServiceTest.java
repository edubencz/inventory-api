package br.com.inventory.interactions.product;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

	@InjectMocks
	ProductService productService;

	@Test
	public void ok() {
		assertThat( "ok", equalTo( "ok" ) );
	}

}
