package com.example.soulborn;

import java.io.Serializable;
import java.util.ArrayList;

public class Leaderboard implements Serializable {
    static ArrayList<Leaderboard> topPlayers;
    private String name;
    private int mostKills;

    public Leaderboard(String name, int mostKills) {
        this.name = name;
        this.mostKills = mostKills;
        topPlayers.add(this);
    }

    public static ArrayList<Leaderboard> getTopPlayers() {
        return topPlayers;
    }

    public static void setTopPlayers(ArrayList<Leaderboard> topPlayers) {
        Leaderboard.topPlayers = topPlayers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMostKills() {
        return mostKills;
    }

    public void setMostKills(int mostKills) {
        this.mostKills = mostKills;
    }
}
