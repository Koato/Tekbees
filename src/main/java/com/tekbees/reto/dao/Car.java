package com.tekbees.reto.dao;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("Modelo de Car")
public class Car {
	
	@ApiModelProperty(value = "Identificador de car")
	private String plate;
}
