package br.com.inventory.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.inventory.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
	Optional<Product> findByCode(String code);
}
