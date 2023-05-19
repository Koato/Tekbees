package com.tekbees.reto.dao;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("Modelo de DriverLocation")
public class DriverLocation extends GenericCoordinates {
	
	@ApiModelProperty(value = "Tipo de DriverLocation")
	private String type;
}
