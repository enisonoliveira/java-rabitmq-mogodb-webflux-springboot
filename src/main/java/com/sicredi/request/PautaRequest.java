package com.sicredi.request;

import com.sicredi.data.PautaData;
import com.sicredi.model.Pauta;
import com.sicredi.response.PautaResponse;

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
