package com.tekbees.reto.dao;

import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;

@lombok.Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("Modelo de Driver")
public class Driver extends GenericFirstLastName {

}
