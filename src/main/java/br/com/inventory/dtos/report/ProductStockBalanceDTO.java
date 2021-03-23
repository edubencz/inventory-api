package br.com.inventory.dtos.report;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ProductStockBalanceDTO {
	private final String code;
	private final String description;
	private final Long outputQuantity;
	private final Integer balanceAvailable;
}
