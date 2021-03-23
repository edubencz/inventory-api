package br.com.inventory.exceptions;

public class ProductUnavailableException extends RuntimeException {
	private static final String PRODUCT_UNAVAILABLE = "Estoque insuficiente para Produto com o código %s";

	public ProductUnavailableException(final String code) {
		super( String.format( PRODUCT_UNAVAILABLE, code ) );
	}
}
