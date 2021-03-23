package br.com.inventory.exceptions;

import java.util.UUID;

public class ProductNotFoundException extends RuntimeException {
	private static final String CODE_NOT_FOUND = "Não foi possível localizar Produto com o código %s";
	private static final String ID_NOT_FOUND = "Não foi possível localizar Produto com o id %s";

	public ProductNotFoundException(final UUID uuid) {
		super( String.format( ID_NOT_FOUND, uuid ) );
	}

	public ProductNotFoundException(final String code) {
		super( String.format( CODE_NOT_FOUND, code ) );
	}

}
