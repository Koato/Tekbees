package com.tekbees.reto.dao;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GenericName {
	
	@ApiModelProperty(value = "Nombre")
	private String name;
}
