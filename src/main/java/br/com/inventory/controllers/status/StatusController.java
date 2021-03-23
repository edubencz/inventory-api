package br.com.inventory.controllers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.inventory.interactions.status.CheckStatus;

@RestController
@RequestMapping("/status")
public class StatusController {

	@Autowired
	private CheckStatus checkStatus;

	@GetMapping
	public String status() {
		return checkStatus.check();
	}

}
