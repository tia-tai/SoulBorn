package com.example.soulborn;

import java.io.Serializable;
import java.util.ArrayList;

public class Game implements Serializable {
    private ArrayList<PlayableCharacter> players = new ArrayList<>();
    private PlayableCharacter currentCharacter;
    private String gameName = "default Game";
    static ArrayList<Game> gameList = new ArrayList<>();

    public Game(ArrayList<PlayableCharacter> players, PlayableCharacter currentCharacter, String gameName) {
        this.players = players;
        this.currentCharacter = currentCharacter;
        this.gameName = gameName;
        gameList.add(this);
    }

    public ArrayList<PlayableCharacter> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<PlayableCharacter> players) {
        this.players = players;
    }

    public PlayableCharacter getCurrentCharacter() {
        return currentCharacter;
    }

    public void setCurrentCharacter(PlayableCharacter currentCharacter) {
        this.currentCharacter = currentCharacter;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public static ArrayList<Game> getGameList() {
        return gameList;
    }

    public static void setGameList(ArrayList<Game> gameList) {
        Game.gameList = gameList;
    }
}
