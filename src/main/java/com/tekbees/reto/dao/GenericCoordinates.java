package com.tekbees.reto.dao;

import java.util.List;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GenericCoordinates {
	
	@ApiModelProperty(value = "Lista de coordenadas")
	private List<Double> coordinates;
}
