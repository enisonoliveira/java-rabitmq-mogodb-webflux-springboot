package com.sicredi.service;

import com.sicredi.dao.UserRepository;
import com.sicredi.model.User;
import com.sicredi.request.UserRequest;
import com.sicredi.serviceimpl.UserImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.DuplicateFormatFlagsException;
import java.util.Optional;

@Service
public class UserService implements UserImpl {

    @Autowired
    private UserRepository repository;

    private Logger logger;



    {
        logger = LoggerFactory.getLogger ( UserService.class );
    }

    public User save( UserRequest userRequest) throws Exception {

        User user=userRequest.toUser (  userRequest );
        noExistCPF(user.getCPF ());
        repository.save(user);

        return user;
    }

    public boolean noExistCPF( String CPF) throws Exception {

        if(repository.findByCPF ( CPF ).isPresent ()){
            throw  new DuplicateFormatFlagsException ( "CPF já existente na base de dados!" );
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
            UserRequest userRequest = new UserRequest ( null , CPF , true );
            user = save (userRequest );
        }
        logger.info ( "CPF encontrado ?: "+userOptional.isPresent () );
        logger.info ( "ID  : "+user.getId () );
        return user;
    }

    public Optional < User > findByCPF( String  CPF) throws Exception {

        return Optional.ofNullable( repository.findByCPF ( CPF ).orElseThrow ( ( ) -> new Exception ( "CPF não encontrado!" ) ) );
    }

}
