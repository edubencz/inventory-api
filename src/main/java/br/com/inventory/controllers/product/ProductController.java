package br.com.inventory.controllers.product;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.inventory.dtos.product.ProductDTO;
import br.com.inventory.interactions.product.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Product", tags = { "Product" })
@RestController
@RequestMapping(value = "product", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

	@Autowired
	private ProductService productService;

	@ApiOperation(value = "Get all products")
	@GetMapping("/all")
	public List<ProductDTO> getAll() {
		return productService.findAll();
	}

	@ApiOperation(value = "Get by code")
	@GetMapping
	public ProductDTO getByCode(@RequestParam final String code) {
		return productService.findByCode( code );
	}

	@ApiOperation(value = "Get by id")
	@GetMapping("{id}")
	public ProductDTO getById(@PathVariable final UUID id) {
		return productService.findById( id );
	}

	@ApiOperation(value = "Save product")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public void save(@RequestBody @Validated final ProductDTO productDTO) {
		productService.save( productDTO );
	}

	@ApiOperation(value = "Update product")
	@PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void update(@PathVariable final UUID id, @RequestBody @Validated final ProductDTO productDTO) {
		productService.update( id, productDTO );
	}

	@ApiOperation(value = "Delete product")
	@DeleteMapping("{id}")
	public void delete(@PathVariable final UUID id) {
		productService.delete( id );
	}

	@ApiOperation(value = "Disable product")
	@DeleteMapping("disable/{id}")
	public void disable(@PathVariable final UUID id) {
		productService.disable( id );
	}
}
