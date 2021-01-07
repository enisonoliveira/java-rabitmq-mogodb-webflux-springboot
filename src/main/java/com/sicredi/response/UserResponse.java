package com.sicredi.response;

import com.google.gson.Gson;
import com.sicredi.data.UserData;
import com.sicredi.model.User;

public class UserResponse  extends UserData {

    public UserResponse ( String id , String CPF , boolean ableToVote ) {
        super ( id , CPF , ableToVote );
    }

    public  UserResponse(){}

    public String userToJson( User user){

        Gson gson = new Gson ();

        UserResponse userResponse = new UserResponse ( user.getId (),user.getCPF (),user.isAbleToVote () );

      return    gson.toJson ( userResponse );
    }
}
