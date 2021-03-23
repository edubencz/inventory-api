package br.com.inventory.interactions.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.inventory.properties.EnvironmentProperties;

@Service
public class CheckStatus {

	@Autowired
	private EnvironmentProperties environmentProperties;

	public String check(){
		return environmentProperties.getStatus();
	}

}
