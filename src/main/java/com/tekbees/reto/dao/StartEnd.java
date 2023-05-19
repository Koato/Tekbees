package com.tekbees.reto.dao;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("Modelo de StartEnd")
public class StartEnd extends GenericDate {
	
	@ApiModelProperty(value = "Dirección de entrega")
	private String pickup_address;
	
	@ApiModelProperty(value = "Lugar de recogida")
	private PickupLocation pickup_location;
}
