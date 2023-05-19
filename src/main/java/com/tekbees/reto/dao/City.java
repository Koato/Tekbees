package com.tekbees.reto.dao;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("Modelo de City")
public class City extends GenericName {

}
