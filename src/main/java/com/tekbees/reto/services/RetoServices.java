package com.tekbees.reto.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tekbees.reto.dao.Viaje;

public interface RetoServices {
	
	Page<Viaje> findAll(Pageable pageable);
	
	Long findAll();
	
	Long findCountry(String country);
	
	Long findCity(String city);
	
	Long findInProgress();

	Viaje findById(String id);

	Viaje guardarViaje(Viaje viajes);

	Viaje actualizarViaje(Viaje viajes);
}
