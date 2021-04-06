package com.pauta.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

import com.pauta.model.User;

public interface UserRepository extends MongoRepository < User, String > {

    Optional < User > findById ( String _id );

    @Query ("{'CPF': ?0}")
    Optional < User > findByCPF ( String CPF );

    User save ( User user );
}