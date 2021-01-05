package com.sicredi.service;

import com.sicredi.dao.UserRepository;
import com.sicredi.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
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

        if(  noExistsCPF ( user.getCPF ( ) )){
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

    public Optional<User> findByCPF(String CPF) throws Exception {

        try {
            return repository.findByCPF ( CPF );
        }catch (Exception e){
            return repository.findById (  repository.findCPF ( CPF ).get ( 0 ).get_id ());
        }

    }

    public boolean existsById ( String _id ) {
        return repository.existsById ( _id );
    }

    public boolean noExistsCPF( String CPF ) {

        try {
            Optional < User > usersUserOptional = repository.findByCPF ( CPF );
            return usersUserOptional.isPresent ();
        }catch (Exception e){
            throw  new  DuplicateFormatFlagsException ( "Mais de um mesmo CPF cadastrado!" );
        }
    }
}
