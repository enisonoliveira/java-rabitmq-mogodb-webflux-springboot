package com.sicredi.data;

public class UserData {

    private String id;

    private String CPF;

    private boolean ableToVote;

    public String getId ( ) {
        return id;
    }

    public UserData (){};

    public UserData ( String id , String CPF , boolean ableToVote ) {
        this.id = id;
        this.CPF = CPF;
        this.ableToVote = ableToVote;
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