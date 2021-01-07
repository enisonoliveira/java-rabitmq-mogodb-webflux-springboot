package com.sicredi.dao;

import com.sicredi.model.SessionVoting;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SessionVotinRepository extends MongoRepository< SessionVoting, String> {

    SessionVoting save( SessionVoting sessionVoting );

    Optional< SessionVoting > findById( String _id);

    @Query("{'user._id' : ?0,'session._id' : ?1}")
    List< SessionVoting > findSessionVotinUser( String user_id, String session_id);

    @Query("{'session._id' : ?0}")
    List< SessionVoting > findSessionVotin( String session_id);

}
