package com.sicredi.serviceimpl;

import com.sicredi.model.User;
import com.sicredi.request.UserRequest;

import java.util.Optional;

public interface UserImpl {

    public User save( UserRequest userRequest) throws Exception;

    public boolean noExistCPF( String CPF) throws Exception;

    public User saveSearchUser(String CPF) throws Exception;

    public Optional < User > findByCPF( String  CPF) throws Exception;

}
