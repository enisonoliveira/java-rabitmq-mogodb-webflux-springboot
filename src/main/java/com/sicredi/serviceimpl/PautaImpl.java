package com.sicredi.serviceimpl;

import com.sicredi.model.Pauta;
import com.sicredi.request.PautaRequest;

import java.util.List;
import java.util.Optional;

public interface PautaImpl {

    public Pauta save ( PautaRequest pautaRequest );

    public Optional < Pauta > findOne( String _id);

    public boolean validateName(String name);

    public Pauta update ( Pauta pauta);

    public Optional < Pauta > findName ( String name );
}
