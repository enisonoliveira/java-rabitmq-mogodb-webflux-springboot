package com.sicredi.response;

import com.google.gson.Gson;
import com.sicredi.data.PautaData;
import com.sicredi.model.Pauta;

public class PautaResponse extends PautaData {
    public PautaResponse ( String id , String name , int totalVoteFavorable , int totalVoteNotFavorable ) {
        super ( id , name , totalVoteFavorable , totalVoteNotFavorable );
    }

    public String toPautaJson(Pauta response) {
        Pauta pauta = new Pauta ( response.getId (),response.getName ()
                ,response.getTotalVoteFavorable (),response.getTotalVoteFavorable () );
        Gson gson = new Gson ();
        return gson.toJson ( pauta );

    }

    public PautaResponse(){
    }
}
