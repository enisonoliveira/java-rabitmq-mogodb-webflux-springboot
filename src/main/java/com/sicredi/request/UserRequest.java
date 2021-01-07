package com.sicredi.request;

import com.sicredi.data.UserData;
import com.sicredi.model.User;

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
