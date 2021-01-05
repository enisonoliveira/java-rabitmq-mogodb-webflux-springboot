package com.sicredi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "pautas")
public class Ruling {

    @Id
    private String _id;

    @Field("name")
    @Indexed(unique = true)
    private String name;

    @Field("total_vote_favorable")
    private int totalVoteFavorable;

    @Field("total_vote_not_favorable")
    private int totalVoteNotFavorable;

    public Ruling (){}

    public Ruling ( String _id, String name, int totalVoteFavorable, int totalVoteNotFavorable) {
        this._id = _id;
        this.name = name;
        this.totalVoteFavorable = totalVoteFavorable;
        this.totalVoteNotFavorable = totalVoteNotFavorable;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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