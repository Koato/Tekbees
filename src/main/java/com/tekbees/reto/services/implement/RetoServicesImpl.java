package com.tekbees.reto.services.implement;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mongodb.client.result.UpdateResult;
import com.tekbees.reto.dao.Viaje;
import com.tekbees.reto.exception.ViajeNotFoundException;
import com.tekbees.reto.repository.RetoRepository;
import com.tekbees.reto.services.RetoServices;

@Service("retoServices")
public class RetoServicesImpl implements RetoServices {

	@Autowired
	private RetoRepository retoRepository;

	@Autowired
	private MongoOperations mongoOperations;

	@Override
	@Transactional(readOnly = true)
	public Page<Viaje> findAll(Pageable pageable) {
		return retoRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Viaje findById(String id) {
		Optional<Viaje> viajes = obtenerViaje(id);
		if (viajes.isPresent()) {
			return viajes.get();
		} else {
			throw new ViajeNotFoundException(id);
		}
	}

	@Override
	@Transactional
	public Viaje guardarViaje(Viaje viaje) {
		retoRepository.save(viaje);
		return obtenerViaje(viaje.getId()).get();
	}

	@Override
	@Transactional
	public Viaje actualizarViaje(Viaje viaje) {
		Query query = new Query(Criteria.where("_id").is(viaje.getId()));
		Update update = new Update();
		update.set("start", viaje.getStart());
		update.set("end", viaje.getEnd());
		update.set("passenger", viaje.getPassenger());
		update.set("country", viaje.getCountry());
		update.set("driver", viaje.getDriver());
		update.set("city", viaje.getCity());
		update.set("car", viaje.getCar());
		update.set("createdAt", viaje.getCreatedAt());
		update.set("updatedAt", viaje.getUpdatedAt());
		update.set("status", viaje.getStatus());
		update.set("price", viaje.getPrice());
		update.set("driver_location", viaje.getDriver_location());
		update.set("check_code", viaje.getCheck_code());
		UpdateResult result = mongoOperations.updateFirst(query, update, Viaje.class);
		if (result.getMatchedCount() == 0) {
			throw new ViajeNotFoundException(viaje.getId());
		}
		return obtenerViaje(viaje.getId()).get();
	}

	@Override
	@Transactional(readOnly = true)
	public Long findCountry(String country) {
		return retoRepository.findAll().stream().filter(x -> x.getCountry().getName().equalsIgnoreCase(country))
				.count();
	}

	@Override
	@Transactional(readOnly = true)
	public Long findCity(String city) {
		return retoRepository.findAll().stream().filter(x -> x.getCity().getName().equalsIgnoreCase(city)).count();
	}

	@Override
	@Transactional(readOnly = true)
	public Long findInProgress() {
		return retoRepository.findAll().stream().filter(x -> !x.getStatus().equals("started")).count();
	}

	@Override
	@Transactional(readOnly = true)
	public Long findAll() {
		return retoRepository.findAll().stream().count();
	}

	private Optional<Viaje> obtenerViaje(String id) {
		Query query = new Query(Criteria.where("_id").is(id));
		return Optional.ofNullable(mongoOperations.findOne(query, Viaje.class));
	}
}
