package com.tekbees.reto.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/reto")
@Api(tags = { "Controlador de viaje" }, description = "Esta API tiene el CRUD de viajes y 3 listados adicionales")
public class RetoController {

	@Autowired
	private RetoServices retoServices;

	private static final String RESPONSE_ERROR = "error";
	private static final String RESPONSE_MENSAJE = "mensaje";

	@GetMapping(value = "/all")
	@ApiOperation(value = "Lista todos los viajes", notes = "Retorna una lista de viajes", response = ResponseEntity.class)
	@ApiResponses({ @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK") })
	public ResponseEntity<Object> getAll(
			@ApiParam(value = "Paginacion de la lista", required = false) @Param(value = "page") Pageable pageable) {
		return ResponseEntity.ok(retoServices.findAll(pageable));
	}

	@GetMapping(value = "/total/travel")
	@ApiOperation(value = "Cantidad de viajes en total", notes = "Retorna una cantidad de viajes", response = ResponseEntity.class)
	@ApiResponses({ @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK") })
	public ResponseEntity<Object> getTravelsTotal() {
		Map<String, Object> response = new HashMap<>();
		response.put("Cantidad", retoServices.findAll());
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/total/country")
	@ApiOperation(value = "Cantidad de viajes por país", notes = "Retorna una cantidad de viajes por país", response = ResponseEntity.class)
	@ApiResponses({ @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK") })
	public ResponseEntity<Object> getTravelsByCountry(
			@ApiParam(value = "País a filtrar", required = true) @Param(value = "country") String country) {
		Map<String, Object> response = new HashMap<>();
		response.put("Cantidad", retoServices.findCountry(country.trim()));
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/total/city")
	@ApiOperation(value = "Cantidad de viajes por ciudad", notes = "Retorna una cantidad de viajes por ciudad", response = ResponseEntity.class)
	@ApiResponses({ @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK") })
	public ResponseEntity<Object> getTravelsByCity(
			@ApiParam(value = "Ciudad a filtrar", required = true) @Param(value = "city") String city) {
		Map<String, Object> response = new HashMap<>();
		response.put("Cantidad", retoServices.findCity(city.trim()));
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/total/progress")
	@ApiOperation(value = "Cantidad de viajes en progreso", notes = "Retorna una cantidad de viajes en progreso", response = ResponseEntity.class)
	@ApiResponses({ @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK") })
	public ResponseEntity<Object> getTravelsInProgress() {
		Map<String, Object> response = new HashMap<>();
		response.put("Cantidad", retoServices.findInProgress());
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/{id}")
	@ApiOperation(value = "Viaje según identificador", notes = "Retorna un viaje según identificador", response = ResponseEntity.class)
	@ApiResponses({ @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK") })
	public ResponseEntity<Object> viaje(
			@ApiParam(value = "Identificador del viaje", required = true) @PathVariable(required = true) String id) {
		Map<String, Object> response = new HashMap<>();
		try {
			return ResponseEntity.ok(retoServices.findById(id));
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
	@ApiOperation(value = "Creación de viaje", notes = "Creación de un nuevo viaje", response = ResponseEntity.class)
	@ApiResponses({ @ApiResponse(code = HttpServletResponse.SC_CREATED, message = "ELEMENTO CREADO"),
			@ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "ERROR EN EL SERVIDOR") })
	public ResponseEntity<Object> insertarViaje(
			@ApiParam(value = "Viaje a registrar", required = true) @RequestBody(required = true) Viaje viaje) {
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
	@ApiOperation(value = "Actualización de viaje", notes = "Actualización de un viaje", response = ResponseEntity.class)
	@ApiResponses({ @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK"),
			@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "ELEMENTO NO ENCONTRADO"),
			@ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "ERROR EN EL SERVIDOR") })
	public ResponseEntity<Object> actualizarViaje(
			@ApiParam(value = "Identificador de viaje", required = true) @PathVariable(required = true) String id,
			@ApiParam(value = "Viaje a actualizar", required = true) @RequestBody(required = true) Viaje viaje) {
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
