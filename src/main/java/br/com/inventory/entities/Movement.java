package br.com.inventory.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "movement")
public class Movement extends Model {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@ManyToOne
	private Product product;

	@Enumerated(EnumType.STRING)
	@Column(name = "movement_type", nullable = false)
	private MovementType movementType;

	@Column(name = "quantity", nullable = false)
	private Integer quantity;

	@Column(name = "value", nullable = false)
	private BigDecimal value;

	@Column(name = "gross_profit", nullable = false)
	private BigDecimal grossProfit = BigDecimal.ZERO;

	@Column(name = "event_at")
	private LocalDateTime eventAt;

}
