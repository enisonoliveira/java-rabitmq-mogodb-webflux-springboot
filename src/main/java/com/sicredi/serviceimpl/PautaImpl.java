package com.sicredi.serviceimpl;

import com.sicredi.model.Pauta;

import java.util.List;
import java.util.Optional;

public interface PautaImpl {

    public Pauta save ( Pauta pauta );

    public Optional < Pauta > findOne( String _id);

    public boolean validateName(String name);

    public Pauta update ( Pauta pauta);

    public List < Pauta > findName ( String name );
}
