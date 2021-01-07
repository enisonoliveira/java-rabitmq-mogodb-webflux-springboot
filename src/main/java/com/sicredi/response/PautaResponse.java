package com.sicredi.response;

import com.google.gson.Gson;
import com.sicredi.data.PautaData;

public class PautaResponse extends PautaData {
    public PautaResponse ( String id , String name , int totalVoteFavorable , int totalVoteNotFavorable ) {
        super ( id , name , totalVoteFavorable , totalVoteNotFavorable );
    }

    public String toPautaJson(PautaResponse response) {

       Gson gson = new Gson ();
        return gson.toJson ( response );
    }
}
