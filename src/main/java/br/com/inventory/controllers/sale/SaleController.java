package br.com.inventory.controllers.sale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.inventory.dtos.sale.SaleDTO;
import br.com.inventory.interactions.sale.SaleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Sale Product", tags = { "Sale" })
@RestController
@RequestMapping(value = "sale", produces = MediaType.APPLICATION_JSON_VALUE)
public class SaleController {

	@Autowired
	private SaleService saleService;

	@ApiOperation(value = "Sell Product")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public void sell(@RequestBody final SaleDTO saleDTO) {
		saleService.sell( saleDTO );
	}

}
