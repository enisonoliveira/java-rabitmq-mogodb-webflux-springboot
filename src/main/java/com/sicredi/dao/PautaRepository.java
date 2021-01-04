package com.sicredi.dao;

import com.sicredi.model.Pauta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PautaRepository extends MongoRepository < Pauta, String > {

    Pauta save(Pauta pauta);

    @Query ("{'name': ?0}")
    List <Pauta> findName( String name);
}
