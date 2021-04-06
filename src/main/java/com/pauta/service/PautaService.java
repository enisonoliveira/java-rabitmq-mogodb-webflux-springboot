package com.pauta.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.DuplicateFormatFlagsException;
import java.util.Optional;

import com.pauta.dao.PautaRepository;
import com.pauta.model.Pauta;
import com.pauta.request.PautaRequest;
import com.pauta.serviceimpl.PautaImpl;

@Service
public class PautaService implements PautaImpl {

    @Autowired
    private PautaRepository repository;

    private Logger logger;

    {
        logger = LoggerFactory.getLogger ( PautaService.class );
    }

    public Pauta save ( PautaRequest pautaRequest ) {

        Pauta pauta = pautaRequest.toPauta ( pautaRequest );

        if(validateName ( pauta.getName () )){
            logger.error ( ": Pauta  "+ pauta.getName () +" já existe no banco" );
            throw  new DuplicateFormatFlagsException ( ": Operação não permitida!" );
        }
        repository.save ( pauta );
        logger.info ( ": save data pauta " );
        logger.info ( ": Operação realizada com sucesso!");
        logger.info ( ": ID:"+ pauta.getId () );
        return pauta;
    }

    public Pauta update ( Pauta pauta ) {


        logger.info ( "pauta id "+pauta.getId () );
        logger.info ( "pauta name "+pauta.getName () );
        logger.info ( "Total voto contra "+pauta.getTotalVoteFavorable () );
        logger.info ( "Total de voto pró "+pauta.getTotalVoteNotFavorable () );
        if( pauta.getId () == null || pauta.getId ().equals ( "" )
                || pauta.getName ().equals ( "" ) || pauta.getName ()==null){
            logger.error ( ":Pauta como dados inválido para alteração" );
            throw  new DuplicateFormatFlagsException ( ":Operação não permitida!" );
        }
        repository.save ( pauta );
        logger.info ( ": Update data pauta" );
        logger.info (": Operação update realizada com sucesso!");
        logger.info (": ID:"+ pauta.getId () );

        return pauta;
    }

    public Optional < Pauta > findOne( String _id) {
       return repository.findById(_id);
    }

    public boolean validateName(String name){

       return findName ( name ).isPresent ();
    }

    public Optional< Pauta > findName ( String name ) {
        return repository.findName ( name );
    }
}
