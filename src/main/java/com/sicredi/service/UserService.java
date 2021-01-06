package com.sicredi.service;

import com.sicredi.dao.UserRepository;
import com.sicredi.model.User;
import com.sicredi.serviceimpl.UserImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserImpl {

    @Autowired
    private UserRepository repository;

    private Logger logger;

    {
        logger = LoggerFactory.getLogger ( UserService.class );
    }

    public User save( User user) throws Exception {

        existCPF(user.getCPF ());
        repository.save(user);

        return user;
    }

    public boolean existCPF( String CPF) throws Exception {

        if(!repository.findByCPF ( CPF ).isPresent ()){
            throw  new Exception ( "CPF já existente na base de dados!" );
        }
        return false;
    }

    public User saveSearchUser(String CPF) throws Exception {

        Optional<User>userOptional = repository.findByCPF ( CPF );
        User user;
        if (userOptional.isPresent () ) {
            user = userOptional.get (  );
        } else {
            //se não existe cadastra um novo usuário com cpf informado
            user = new User ( null , CPF , true );
            user = save ( user );
        }
        return user;
    }
    public Optional < User > findByCPF( String  CPF){
       return repository.findByCPF ( CPF );
    }

}
