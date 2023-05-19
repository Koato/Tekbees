package com.tekbees.reto.dao;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class GenericFirstLastName {
	
	@JsonProperty("first_name")
	private String first_name;
	
	@JsonProperty("last_name")
	private String last_name;
}
