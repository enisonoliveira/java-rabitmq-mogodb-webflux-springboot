package com.sicredi.dao;

import com.sicredi.model.Pauta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PautaRepository extends MongoRepository < Pauta, String > {

    Pauta save( Pauta pauta );

    @Query ("{'name': ?0}")
    Optional < Pauta > findName( String name);
}
