package com.pauta.dao;

import java.util.Optional;

import com.pauta.model.Pauta;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface PautaRepository extends MongoRepository < Pauta, String > {

    Pauta save( Pauta pauta );

    @Query ("{'name': ?0}")
    Optional < Pauta > findName( String name);
}
