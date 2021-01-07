package com.sicredi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document ( collection = "users" )
public class User {

    @Id
    private String id;

    @Indexed(unique = true)
    @Field ( "CPF" )
    private String CPF;

    @Field ( "able_to_vote" )
    private boolean ableToVote;

    public User ( String id , String CPF , boolean ableToVote ) {
        this.id = id;
        this.CPF = CPF;
        this.ableToVote = ableToVote;
    }

    public String getId ( ) {
        return id;
    }

    public void setId ( String id ) {
        this.id = id;
    }

    public String getCPF ( ) {
        return CPF;
    }

    public void setCPF ( String CPF ) {
        this.CPF = CPF;
    }

    public boolean isAbleToVote ( ) {
        return ableToVote;
    }

    public void setAbleToVote ( boolean ableToVote ) {
        this.ableToVote = ableToVote;
    }
}