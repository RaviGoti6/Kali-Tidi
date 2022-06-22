package com.example.kalitidi;

public class Player {

    public String Id;
    public String PlayerName;
    public String Points;

    public Player(String id, String playerName) {
        Id = id;
        PlayerName = playerName;
        //Points = points;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getPlayerName() {
        return PlayerName;
    }

    public void setPlayerName(String playerName) {
        PlayerName = playerName;
    }

    public String getPoints() {
        return Points;
    }

    public void setPoints(String points) {
        Points = points;
    }
}
