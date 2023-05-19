package com.tekbees.reto.dao;

import java.util.Date;
import javax.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Document(collection = "Viajes")
public class Viaje {
	
	@Id
	private String id;
	private StartEnd start;
	private StartEnd end;
	
	@JsonProperty("driver_location")
	private DriverLocation driver_location;
	
	private Passenger passenger;
	private Country country;
	private Driver driver;
	private City city;
	private Car car;
	private Date createdAt;
	private Date updatedAt;
	private String status;
	private Integer price;
	
	@JsonProperty("check_code")
	private String check_code;
}
