package com.tekbees.reto.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.tekbees.reto.dao.Viaje;

@Repository
public interface RetoRepository extends MongoRepository<Viaje, String> {

}
