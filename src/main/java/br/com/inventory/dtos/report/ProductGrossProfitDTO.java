package br.com.inventory.dtos.report;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ProductGrossProfitDTO {
	private final String code;
	private final String description;
	private final Long outputQuantity;
	private final BigDecimal grossProfit;
}
