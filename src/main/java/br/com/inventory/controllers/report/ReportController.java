package br.com.inventory.controllers.report;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.inventory.dtos.report.ProductGrossProfitDTO;
import br.com.inventory.dtos.report.ProductStockBalanceDTO;
import br.com.inventory.entities.ProductType;
import br.com.inventory.interactions.report.ReportService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Report", tags = { "Report" })
@RestController
@RequestMapping(value = "report", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReportController {

	@Autowired
	private ReportService reportService;

	@ApiOperation(value = "Report by productType")
	@GetMapping(value = "stock-balance/{productType}")
	public List<ProductStockBalanceDTO> stockBalance(@PathVariable final ProductType productType) {
		return reportService.stockBalance( productType );
	}

	@ApiOperation(value = "Report by productCode")
	@GetMapping(value = "gross-profit/{productCode}")
	public ProductGrossProfitDTO grossProfit(@PathVariable final String productCode) {
		return reportService.grossProfit( productCode );
	}

}
