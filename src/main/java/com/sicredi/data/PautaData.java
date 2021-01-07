package com.sicredi.data;

public class PautaData {

    private String id;

    private String name;

    private int totalVoteFavorable;

    public PautaData ( String id , String name , int totalVoteFavorable , int totalVoteNotFavorable ) {
        this.id = id;
        this.name = name;
        this.totalVoteFavorable = totalVoteFavorable;
        this.totalVoteNotFavorable = totalVoteNotFavorable;
    }

    public PautaData(){}

    private int totalVoteNotFavorable;


    public String getId ( ) {
        return id;
    }

    public void setId ( String id ) {
        this.id = id;
    }

    public String getName ( ) {
        return name;
    }

    public void setName ( String name ) {
        this.name = name;
    }

    public int getTotalVoteFavorable ( ) {
        return totalVoteFavorable;
    }

    public void setTotalVoteFavorable ( int totalVoteFavorable ) {
        this.totalVoteFavorable = totalVoteFavorable;
    }

    public int getTotalVoteNotFavorable ( ) {
        return totalVoteNotFavorable;
    }

    public void setTotalVoteNotFavorable ( int totalVoteNotFavorable ) {
        this.totalVoteNotFavorable = totalVoteNotFavorable;
    }

}