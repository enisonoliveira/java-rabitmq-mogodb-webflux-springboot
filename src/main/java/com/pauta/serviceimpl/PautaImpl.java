package com.pauta.serviceimpl;

import java.util.Optional;

import com.pauta.model.Pauta;
import com.pauta.request.PautaRequest;

public interface PautaImpl {

    public Pauta save ( PautaRequest pautaRequest );

    public Optional < Pauta > findOne( String _id);

    public boolean validateName(String name);

    public Pauta update ( Pauta pauta);

    public Optional < Pauta > findName ( String name );
}
