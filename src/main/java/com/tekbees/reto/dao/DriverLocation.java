package com.tekbees.reto.dao;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DriverLocation extends GenericCoordinates {
	private String type;
}
