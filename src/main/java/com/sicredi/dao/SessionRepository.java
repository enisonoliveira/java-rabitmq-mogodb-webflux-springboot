package com.sicredi.dao;

import com.sicredi.model.Session;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface SessionRepository extends MongoRepository<Session, String> {

    Optional < Session > findById( String id);

    Session save( Session session);

    @Query ("{'pauta._id': ?0}")
    Optional< Session> findPautaId ( String id );

}
