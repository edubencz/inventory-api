package br.com.inventory.properties;

import lombok.Getter;
import lombok.Setter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter

@Configuration
@ConfigurationProperties
public class EnvironmentProperties {

	private String status;
}
