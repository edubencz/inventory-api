package br.com.inventory.entities;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import br.com.inventory.dtos.product.ProductDTO;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "product")
public class Product extends Model {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Enumerated(EnumType.STRING)
	@Column(name = "product_type", nullable = false)
	private ProductType productType;

	@Column(name = "code", nullable = false, unique = true)
	private String code;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "value", nullable = false)
	private BigDecimal value;

	@Column(name = "quantity", nullable = false)
	private Integer quantity;

	@Column(name = "enabled", nullable = false)
	private Boolean enabled;

	public ProductDTO from() {
		return ProductDTO.builder()
				.id( getId() )
				.productType( getProductType() )
				.code( getCode() )
				.description( getDescription() )
				.value( getValue() )
				.quantity( getQuantity() )
				.enabled( getEnabled() )
				.build();
	}

}
