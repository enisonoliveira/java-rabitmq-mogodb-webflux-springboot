package com.sicredi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "pautas")
public class Pauta {

    @Id
    private String _id;

    @Field("name")
    @Indexed(unique = true)
    private String name;

    @Field("total_vote_favorable")
    private int totalVoteFavorable;

    @Field("total_vote_not_favorable")
    private int totalVoteNotFavorable;

    public Pauta (){}

    public Pauta ( String _id, String name, int totalVoteFavorable, int totalVoteNotFavorable) {
        this.name = name;
        this.totalVoteFavorable = totalVoteFavorable;
        this.totalVoteNotFavorable = totalVoteNotFavorable;
    }

    public String getId ( ) {
        return _id;
    }

    public void setId ( String id ) {
        this._id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalVoteFavorable() {
        return totalVoteFavorable;
    }

    public void setTotalVoteFavorable(int totalVoteFavorable) {
        this.totalVoteFavorable = totalVoteFavorable;
    }

    public int getTotalVoteNotFavorable() {
        return totalVoteNotFavorable;
    }

    public void setTotalVoteNotFavorable(int totalVoteNotFavorable) {
        this.totalVoteNotFavorable = totalVoteNotFavorable;
    }

}