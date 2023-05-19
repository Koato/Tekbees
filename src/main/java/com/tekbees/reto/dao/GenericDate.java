package com.tekbees.reto.dao;

import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GenericDate {
	
	@ApiModelProperty(value = "Date")
	private Date date;
}
