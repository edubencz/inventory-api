package br.com.inventory.controllers.purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.inventory.dtos.purchase.PurchaseDTO;
import br.com.inventory.interactions.purchase.PurchaseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Purchase", tags = { "Purchase" })
@RestController
@RequestMapping(value = "purchase", produces = MediaType.APPLICATION_JSON_VALUE)
public class PurchaseController {

	@Autowired
	private PurchaseService purchaseService;

	@ApiOperation(value = "Purchase Product")
	@PostMapping
	public void purchase(@RequestBody final PurchaseDTO purchaseDTO) {
		purchaseService.purchase( purchaseDTO );
	}

}
