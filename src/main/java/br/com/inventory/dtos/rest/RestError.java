package br.com.inventory.dtos.rest;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class RestError {
	private final String title;
	private final String description;
	private final Collection<String> messages;
}
