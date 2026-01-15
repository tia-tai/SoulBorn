package com.example.soulborn;

import javafx.scene.image.Image;

import java.io.Serializable;

public class PlayableCharacter implements Serializable {
    private String username;
    private int kills;
    private boolean alive;
    private Weapon weapon;
    private int bullets;
    private int hp;
    private boolean player;
    transient private Image icon;
    private int defaultPower;
    private int defaultDexterity;
    private int defaultFaith;
    private int defaultArmor;
    private int defaultIntelligence;
    private int power;
    private int dexterity;
    private int faith;
    private int armor;
    private int intelligence;
    private int level;
    private int exp;

    public PlayableCharacter(String username, int kills, boolean alive, Weapon weapon, int bullets, int hp, boolean player, Image icon, int defaultPower, int defaultDexterity, int defaultFaith, int defaultArmor, int defaultIntelligence, int power, int dexterity, int faith, int armor, int intelligence, int level, int exp) {
        this.username = username;
        this.kills = kills;
        this.alive = alive;
        this.weapon = weapon;
        this.bullets = bullets;
        this.hp = hp;
        this.player = player;
        this.icon = icon;
        this.defaultPower = defaultPower;
        this.defaultDexterity = defaultDexterity;
        this.defaultFaith = defaultFaith;
        this.defaultArmor = defaultArmor;
        this.defaultIntelligence = defaultIntelligence;
        this.power = power;
        this.dexterity = dexterity;
        this.faith = faith;
        this.armor = armor;
        this.intelligence = intelligence;
        this.level = level;
        this.exp = exp;
    }

    public int getBullets() {
        return bullets;
    }

    public void setBullets(int bullets) {
        this.bullets = bullets;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public boolean isPlayer() {
        return player;
    }

    public void setPlayer(boolean player) {
        this.player = player;
    }

    public Image getIcon() {
        return icon;
    }

    public void setIcon(Image icon) {
        this.icon = icon;
    }

    public int getDefaultPower() {
        return defaultPower;
    }

    public void setDefaultPower(int defaultPower) {
        this.defaultPower = defaultPower;
    }

    public int getDefaultDexterity() {
        return defaultDexterity;
    }

    public void setDefaultDexterity(int defaultDexterity) {
        this.defaultDexterity = defaultDexterity;
    }

    public int getDefaultFaith() {
        return defaultFaith;
    }

    public void setDefaultFaith(int defaultFaith) {
        this.defaultFaith = defaultFaith;
    }

    public int getDefaultArmor() {
        return defaultArmor;
    }

    public void setDefaultArmor(int defaultArmor) {
        this.defaultArmor = defaultArmor;
    }

    public int getDefaultIntelligence() {
        return defaultIntelligence;
    }

    public void setDefaultIntelligence(int defaultIntelligence) {
        this.defaultIntelligence = defaultIntelligence;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getFaith() {
        return faith;
    }

    public void setFaith(int faith) {
        this.faith = faith;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }
}