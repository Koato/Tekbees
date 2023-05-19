package com.tekbees.reto.dao;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class StartEnd extends GenericDate {
	
	@JsonProperty("pickup_address")
	private String pickup_address;
	
	@JsonProperty("pickup_location")
	private PickupLocation pickup_location;
}
