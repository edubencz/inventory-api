package br.com.inventory.dtos.sale;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import br.com.inventory.entities.Movement;
import br.com.inventory.entities.MovementType;
import br.com.inventory.entities.Product;

@Getter
@Builder
@AllArgsConstructor
public class SaleDTO {
	private final String productCode;
	private final Integer quantity;
	private final BigDecimal value;
	private final LocalDateTime saleAt;

	public Movement from(final Product product) {
		return Movement.builder()
				.product( product )
				.movementType( MovementType.OUT )
				.value( getValue() )
				.quantity( getQuantity() )
				.eventAt( getSaleAt() )
				.grossProfit( calculateGrossProfit( product.getValue() ) )
				.build();
	}

	private BigDecimal calculateGrossProfit(final BigDecimal basicValue) {
		return getValue()
				.multiply( BigDecimal.valueOf( getQuantity() ) )
				.subtract( basicValue.multiply( BigDecimal.valueOf( getQuantity() ) ) );
	}
}
