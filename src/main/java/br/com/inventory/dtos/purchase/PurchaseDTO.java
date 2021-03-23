package br.com.inventory.dtos.purchase;

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
public class PurchaseDTO {
	private final String productCode;
	private final Integer quantity;
	private final BigDecimal value;
	private final LocalDateTime purchaseAt;

	public Movement from(final Product product) {
		return Movement.builder()
				.product( product )
				.movementType( MovementType.IN )
				.value( getValue() )
				.quantity( getQuantity() )
				.eventAt( getPurchaseAt() )
				.grossProfit(BigDecimal.ZERO )
				.build();
	}
}
