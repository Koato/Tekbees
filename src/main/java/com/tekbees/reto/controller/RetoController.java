package com.tekbees.reto.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tekbees.reto.dao.Viaje;
import com.tekbees.reto.exception.ViajeNotFoundException;
import com.tekbees.reto.services.RetoServices;

@RestController
@RequestMapping(value = "/reto")
public class RetoController {

	@Autowired
	private RetoServices retoServices;

	private static final String RESPONSE_ERROR = "error";
	private static final String RESPONSE_MENSAJE = "mensaje";

	@GetMapping(value = "/all")
	public ResponseEntity<Object> getAll(@Param(value = "page") Pageable pageable) {
		return ResponseEntity.ok(retoServices.findAll(pageable));
	}

	@GetMapping(value = "/total/travel")
	public ResponseEntity<Object> getTravelsTotal() {
		Map<String, Object> response = new HashMap<>();
		response.put("Cantidad", retoServices.findAll());
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/total/country")
	public ResponseEntity<Object> getTravelsByCountry(@Param(value = "country") String country) {
		Map<String, Object> response = new HashMap<>();
		response.put("Cantidad", retoServices.findCountry(country.trim()));
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/total/city")
	public ResponseEntity<Object> getTravelsByCity(@Param(value = "city") String city) {
		Map<String, Object> response = new HashMap<>();
		response.put("Cantidad", retoServices.findCity(city.trim()));
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/total/progress")
	public ResponseEntity<Object> getTravelsInProgress() {
		Map<String, Object> response = new HashMap<>();
		response.put("Cantidad", retoServices.findInProgress());
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> viaje(@PathVariable(required = true) String id) {
		Map<String, Object> response = new HashMap<>();
		Viaje viajes = null;
		try {
			viajes = retoServices.findById(id);
			return ResponseEntity.ok(viajes);
		} catch (ViajeNotFoundException e) {
			response.put(RESPONSE_ERROR, e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		} catch (DataAccessException e) {
			response.put(RESPONSE_MENSAJE, "Error al realizar la consulta en la base de datos");
			response.put(RESPONSE_ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping
	public ResponseEntity<Object> insertarUsuario(@RequestBody(required = true) Viaje viaje) {
		Map<String, Object> response = new HashMap<>();
		try {
			Viaje viajesNuevo = retoServices.guardarViaje(viaje);
			response.put(RESPONSE_MENSAJE, "Ha sido insertado con éxito");
			response.put("viaje", viajesNuevo);
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} catch (DataAccessException e) {
			response.put(RESPONSE_MENSAJE, "Error al insertarlo en la base de datos");
			response.put(RESPONSE_ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Object> actualizarUsuario(@PathVariable(required = true) String id,
			@RequestBody(required = true) Viaje viaje) {
		Map<String, Object> response = new HashMap<>();
		try {
			viaje.setId(id);
			retoServices.actualizarViaje(viaje);
			response.put(RESPONSE_MENSAJE, "Ha sido actualizado con éxito");
			response.put("viaje", viaje);
			return ResponseEntity.ok(response);
		} catch (ViajeNotFoundException e) {
			response.put(RESPONSE_ERROR, e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		} catch (DataAccessException e) {
			response.put(RESPONSE_MENSAJE, "Error al actualizarlo en la base de datos");
			response.put(RESPONSE_ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
