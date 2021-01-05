package com.sicredi.service;

import com.sicredi.dao.PautaRepository;
import com.sicredi.model.Ruling;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.DuplicateFormatFlagsException;
import java.util.List;
import java.util.Optional;

@Service
public class RulingService {

    @Autowired
    private PautaRepository repository;

    private Logger logger;

    {
        logger = LoggerFactory.getLogger ( RulingService.class );
    }

    public Ruling save ( Ruling ruling ) {

        if(!validateName ( ruling.getName ()  )||!( ruling.get_id ()==null)){
            logger.error ( ": Pauta  "+ ruling.getName () +" já existe no banco" );
            throw  new DuplicateFormatFlagsException ( ": Operação não permitida!" );
        }
        repository.save ( ruling );
        logger.info ( ": save data pauta " );
        logger.info ( ": Operação realizada com sucesso!");
        logger.info ( ": ID:"+ ruling.get_id () );
        return ruling;
    }

    protected Ruling update ( Ruling ruling ) {

        if( ruling.get_id () == null || ruling.get_id ().equals ( "" )
                || ruling.getName ().equals ( "" ) || ruling.getName ()==null){
            logger.error ( ":Pauta como dados inválido para alteração" );
            throw  new DuplicateFormatFlagsException ( ":Operação não permitida!" );
        }
        repository.save ( ruling );
        logger.info ( ": Update data pauta" );
        logger.info (": Operação update realizada com sucesso!");
        logger.info (": ID:"+ ruling.get_id () );

        return ruling;
    }

    public Ruling delete ( Ruling ruling ) {

        if( ruling.get_id () == null || ruling.get_id ().equals ( "" )){
            logger.error ( ": Dados inválidos  para operação!" );
            throw  new DuplicateFormatFlagsException ( ": Operação não permitida!" );
        }
        if (!validateId( ruling.get_id ())){
            logger.error ( ": Pauta desconhecida no sistema!" );
            throw  new DuplicateFormatFlagsException ( ": Operação não permitida!" );
        }
        repository.delete ( ruling );
        logger.info ( ": Pauta deletada " );
        logger.info ( ": Operação delete realizada com sucesso!");
        logger.info ( ": ID:"+ ruling.get_id () );

        return ruling;
    }


    protected Optional < Ruling > findOne( String _id) {
       return repository.findById(_id);
    }

    public boolean validateName(String name){

        List< Ruling > rulings =findName ( name );

        if( rulings ==null|| rulings.size ()==0){
            return true;
        }

        return false;
    }

    private boolean validateId(String _id ){

        return repository.existsById ( _id );
    }

    protected List< Ruling > findName ( String name ) {
        return repository.findName ( name );
    }
}
