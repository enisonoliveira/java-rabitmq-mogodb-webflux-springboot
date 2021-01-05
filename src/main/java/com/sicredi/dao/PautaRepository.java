package com.sicredi.dao;

import com.sicredi.model.Ruling;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PautaRepository extends MongoRepository < Ruling, String > {

    Ruling save( Ruling ruling );

    @Query ("{'name': ?0}")
    List < Ruling > findName( String name);
}
