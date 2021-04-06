package com.pauta.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

import com.pauta.model.Session;

public interface SessionRepository extends MongoRepository<Session, String> {

    Optional < Session > findById( String id);

    Session save( Session session);

    @Query ("{'pauta._id': ?0}")
    Optional< Session> findPautaId ( String id );

}
