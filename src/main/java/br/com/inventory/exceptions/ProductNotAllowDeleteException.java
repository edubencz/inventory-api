package br.com.inventory.exceptions;

import java.util.UUID;

public class ProductNotAllowDeleteException extends RuntimeException {
	private static final String ID_NOT_ALLOW_DELETE = "Produto com o id %s não pode ser excluído.";

	public ProductNotAllowDeleteException(final UUID uuid) {
		super( String.format( ID_NOT_ALLOW_DELETE, uuid ) );
	}
}
