package com.sicredi.service;

import com.sicredi.dao.UserRepository;
import com.sicredi.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.DuplicateFormatFlagsException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    private Logger logger;

    {
        logger = LoggerFactory.getLogger ( UserService.class );
    }

    public User save( User user){

        if( existsCPF ( user.getCPF ( ) )){
            logger.error ( "Usuário ja existe no banco com esse CPF" );
            throw  new DuplicateFormatFlagsException ( "Usuário ja existe no banco com esse CPF" );
        }
        repository.save(user);

        return user;
    }

    public List<User> findAll(){
        return repository.findAll();
    }

    public long count(){
        return repository.count();
    }

    public void delete(User user) {
        repository.delete(user);
    }

    public Optional<User> findById(String _id) {
        return repository.findById(_id);
    }

    public List<User> findByCPF(String CPF) {
        return repository.findByCPF ( CPF );
    }

    public boolean existsById ( String _id ) {
        return repository.existsById ( _id );
    }

    public boolean existsCPF( String CPF ) {

        List < User > users=repository.findByCPF ( CPF );
        return users.size ( ) == 0;
    }
}
