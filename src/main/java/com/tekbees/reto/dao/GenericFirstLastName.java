package com.tekbees.reto.dao;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GenericFirstLastName {
	
	@ApiModelProperty(value = "Primer nombre")
	private String first_name;
	
	@ApiModelProperty(value = "Apellido")
	private String last_name;
}
