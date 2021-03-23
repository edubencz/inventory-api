package br.com.inventory.exceptions.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.inventory.dtos.rest.RestError;

public abstract class BaseExceptionHandler {

	protected static final String DEFAULT_ERROR_TITLE_404 = "Registro não localizado";
	protected static final String DEFAULT_ERROR_TITLE_400 = "Não foi possível processar a requisição";

	protected ResponseEntity<RestError> buildResponse(HttpStatus httpStatus, final String title,
			final String description) {
		return ResponseEntity.status( httpStatus )
				.body( RestError.builder()
						.title( title )
						.description( description )
						.build() );
	}
}
