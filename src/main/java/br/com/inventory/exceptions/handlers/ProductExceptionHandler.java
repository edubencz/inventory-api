package br.com.inventory.exceptions.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.inventory.dtos.rest.RestError;
import br.com.inventory.exceptions.ProductDisabledException;
import br.com.inventory.exceptions.ProductNotAllowDeleteException;
import br.com.inventory.exceptions.ProductNotFoundException;
import br.com.inventory.exceptions.ProductUnavailableException;

@ControllerAdvice
public class ProductExceptionHandler extends BaseExceptionHandler {

	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<RestError> onError(ProductNotFoundException ex) {
		return buildResponse( HttpStatus.NOT_FOUND, DEFAULT_ERROR_TITLE_404, ex.getMessage() );
	}

	@ExceptionHandler(ProductDisabledException.class)
	public ResponseEntity<RestError> onError(ProductDisabledException ex) {
		return buildResponse( HttpStatus.BAD_REQUEST, DEFAULT_ERROR_TITLE_404, ex.getMessage() );
	}

	@ExceptionHandler(ProductUnavailableException.class)
	public ResponseEntity<RestError> onError(ProductUnavailableException ex) {
		return buildResponse( HttpStatus.BAD_REQUEST, DEFAULT_ERROR_TITLE_404, ex.getMessage() );
	}

	@ExceptionHandler(ProductNotAllowDeleteException.class)
	public ResponseEntity<RestError> onError(ProductNotAllowDeleteException ex) {
		return buildResponse( HttpStatus.BAD_REQUEST, DEFAULT_ERROR_TITLE_400, ex.getMessage() );
	}

}
