package br.com.inventory.controllers.status;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.inventory.Application;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
class StatusControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testStatus() {
		String body = restTemplate.getForObject( "/status", String.class );
		assertThat( body, equalTo( "OK" ) );
	}

}
