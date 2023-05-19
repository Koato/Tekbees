package com.tekbees.reto.dao;

import java.util.Date;
import javax.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Document(collection = "Viajes")
@ApiModel("Modelo de Viaje")
public class Viaje {
	
	@Id
	@ApiModelProperty(value = "Identificador de viaje")
	private String id;
	
	@ApiModelProperty(value = "Inicio de viaje")
	private StartEnd start;
	
	@ApiModelProperty(value = "Fin de viaje")
	private StartEnd end;
	
	@ApiModelProperty(value = "Ubicación del conductor")
	private DriverLocation driver_location;
	
	@ApiModelProperty(value = "Pasajero")
	private Passenger passenger;
	
	@ApiModelProperty(value = "País")
	private Country country;
	
	@ApiModelProperty(value = "Conductor")
	private Driver driver;
	
	@ApiModelProperty(value = "Ciudad")
	private City city;
	
	@ApiModelProperty(value = "Car")
	private Car car;
	
	@ApiModelProperty(value = "Creación")
	private Date createdAt;
	
	@ApiModelProperty(value = "Actualización")
	private Date updatedAt;
	
	@ApiModelProperty(value = "Estado")
	private String status;
	
	@ApiModelProperty(value = "Precio")
	private Integer price;
	
	@ApiModelProperty(value = "Código de verificación")
	private String check_code;
}
