package com.pauta.request;

import com.pauta.data.PautaData;
import com.pauta.model.Pauta;

public class PautaRequest extends PautaData {

    public PautaRequest ( String id , String name , int totalVoteFavorable , int totalVoteNotFavorable ) {
        super ( id , name , totalVoteFavorable , totalVoteNotFavorable );
    }

    public Pauta toPauta( PautaRequest request) {
        Pauta pauta = new Pauta ( request.getId (),request.getName ()
                ,request.getTotalVoteFavorable (),request.getTotalVoteFavorable () );
        return pauta;

    }
}
