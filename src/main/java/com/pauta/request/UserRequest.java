package com.pauta.request;

import com.pauta.data.UserData;
import com.pauta.model.User;

public class UserRequest  extends UserData {
    public UserRequest ( String id , String CPF , boolean ableToVote ) {
        super ( id , CPF , ableToVote );
    }

    public User toUser(UserRequest userRequest){
        User user = new User (userRequest.getId (),userRequest.getCPF ()
                ,userRequest.isAbleToVote ()  );
        return user;
    }
}
