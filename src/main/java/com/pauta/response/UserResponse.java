package com.pauta.response;

import com.google.gson.Gson;
import com.pauta.data.UserData;

public class UserResponse  extends UserData {

    public UserResponse ( String id , String CPF , boolean ableToVote ) {
        super ( id , CPF , ableToVote );
    }

    public  UserResponse(){}

    public String userToJson( UserResponse user){

        Gson gson = new Gson ();
      return    gson.toJson ( user );
    }
}
