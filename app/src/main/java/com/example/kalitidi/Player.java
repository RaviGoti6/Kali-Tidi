package com.example.kalitidi;

public class Player {

    public String Id;
    public String PlayerName;
    public Integer Points;

    public Player(String Id, String PlayerName, Integer Points) {
        this.Id = Id;
        this.PlayerName = PlayerName;
        this.Points = Points;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        Id = Id;
    }

    public String getPlayerName() {
        return PlayerName;
    }

    public void setPlayerName(String PlayerName) {
        PlayerName = PlayerName;
    }

    public Integer getPoints() {
        return Points;
    }

    public void setPoints(Integer Points) {
        Points = Points;
    }
}