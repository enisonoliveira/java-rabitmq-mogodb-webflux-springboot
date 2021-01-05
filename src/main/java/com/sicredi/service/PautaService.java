package com.sicredi.service;

import com.sicredi.dao.PautaRepository;
import com.sicredi.model.Pauta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.DuplicateFormatFlagsException;
import java.util.List;
import java.util.Optional;

@Service
public class PautaService {

    @Autowired
    private PautaRepository repository;

    private Logger logger;

    {
        logger = LoggerFactory.getLogger ( PautaService.class );
    }

    public Pauta save ( Pauta pauta ) {

        if(!validateName ( pauta.getName ()  )||!( pauta.get_id ()==null)){
            logger.error ( ": Pauta  "+ pauta.getName () +" já existe no banco" );
            throw  new DuplicateFormatFlagsException ( ": Operação não permitida!" );
        }
        repository.save ( pauta );
        logger.info ( ": save data pauta " );
        logger.info ( ": Operação realizada com sucesso!");
        logger.info ( ": ID:"+ pauta.get_id () );
        return pauta;
    }

    protected Pauta update ( Pauta pauta ) {

        if( pauta.get_id () == null || pauta.get_id ().equals ( "" )
                || pauta.getName ().equals ( "" ) || pauta.getName ()==null){
            logger.error ( ":Pauta como dados inválido para alteração" );
            throw  new DuplicateFormatFlagsException ( ":Operação não permitida!" );
        }
        repository.save ( pauta );
        logger.info ( ": Update data pauta" );
        logger.info (": Operação update realizada com sucesso!");
        logger.info (": ID:"+ pauta.get_id () );

        return pauta;
    }

    public Pauta delete ( Pauta pauta ) {

        if( pauta.get_id () == null || pauta.get_id ().equals ( "" )){
            logger.error ( ": Dados inválidos  para operação!" );
            throw  new DuplicateFormatFlagsException ( ": Operação não permitida!" );
        }
        if (!validateId( pauta.get_id ())){
            logger.error ( ": Pauta desconhecida no sistema!" );
            throw  new DuplicateFormatFlagsException ( ": Operação não permitida!" );
        }
        repository.delete ( pauta );
        logger.info ( ": Pauta deletada " );
        logger.info ( ": Operação delete realizada com sucesso!");
        logger.info ( ": ID:"+ pauta.get_id () );

        return pauta;
    }


    protected Optional < Pauta > findOne( String _id) {
       return repository.findById(_id);
    }

    public boolean validateName(String name){

        List< Pauta > pautas =findName ( name );

        if( pautas ==null|| pautas.size ()==0){
            return true;
        }

        return false;
    }

    private boolean validateId(String _id ){

        return repository.existsById ( _id );
    }

    protected List< Pauta > findName ( String name ) {
        return repository.findName ( name );
    }
}
