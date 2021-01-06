package com.sicredi.serviceimpl;

import com.sicredi.model.User;

import java.util.Optional;

public interface UserImpl {


    public User save( User user) throws Exception;

    public boolean existCPF( String CPF) throws Exception;

    public User saveSearchUser(String CPF) throws Exception;

    public Optional < User > findByCPF( String  CPF);

}
