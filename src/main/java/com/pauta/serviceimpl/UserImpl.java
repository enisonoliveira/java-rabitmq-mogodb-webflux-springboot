package com.pauta.serviceimpl;

import java.util.Optional;

import com.pauta.model.User;
import com.pauta.request.UserRequest;

public interface UserImpl {

    public User save( UserRequest userRequest) throws Exception;

    public boolean noExistCPF( String CPF) throws Exception;

    public User saveSearchUser(String CPF) throws Exception;

    public Optional < User > findByCPF( String  CPF) throws Exception;

}
