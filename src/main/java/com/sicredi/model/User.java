package com.sicredi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document ( collection = "users" )
public class User {

    @Id
    private String _id;

    @Indexed(unique = true)
    @Field ( "CPF" )
    private String CPF;

    @Field ( "able_to_vote" )
    private boolean ABLE_TO_VOTE;

    public User ( String _id , String CPF , boolean ABLE_TO_VOTE ) {
        this._id = _id;
        this.CPF = CPF;
        this.ABLE_TO_VOTE = ABLE_TO_VOTE;
    }

    public String get_id ( ) {
        return _id;
    }

    public void set_id ( String _id ) {
        this._id = _id;
    }

    public String getCPF ( ) {
        return CPF;
    }

    public void setCPF ( String CPF ) {
        this.CPF = CPF;
    }

    public boolean isABLE_TO_VOTE ( ) {
        return ABLE_TO_VOTE;
    }

    public void setABLE_TO_VOTE ( boolean ABLE_TO_VOTE ) {
        this.ABLE_TO_VOTE = ABLE_TO_VOTE;
    }
}