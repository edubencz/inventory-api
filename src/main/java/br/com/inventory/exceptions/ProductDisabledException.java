package br.com.inventory.exceptions;

public class ProductDisabledException extends RuntimeException {

	private static final String PRODUCT_DISABLED = "O Produto com o código %s não esta disponível";

	public ProductDisabledException(final String code) {
		super( String.format( PRODUCT_DISABLED, code ) );
	}
}
