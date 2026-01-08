package com.example.soulborn;

import javafx.scene.image.Image;

import java.util.Random;

public class Player extends PlayableCharacter{
    public Random random = new Random();

    public Player(String username, int kills, boolean alive, Weapon weapon, int bullets, int hp, boolean player, Image icon, int defaultPower, int defaultDexterity, int defaultFaith, int defaultArmor, int defaultIntelligence, int power, int dexterity, int faith, int armor, int intelligence, int level, int exp) {
        super(username, kills, alive, weapon, bullets, hp, player, icon, defaultPower, defaultDexterity, defaultFaith, defaultArmor, defaultIntelligence, power, dexterity, faith, armor, intelligence, level, exp);
    }

    public String attack(PlayableCharacter name) {
        int damage = this.getWeapon().getDamage();
        int hitOrMiss = random.nextInt(0, 100);
        if (hitOrMiss <= this.getWeapon().getAccuracy()) {
            name.setHp(name.getHp()-damage);
            if (name.getHp() == 0) {
                return name.getUsername() + " died";
            } else {
                return "Hit for " + damage;
            }
        } else {
            return "Missed";
        }
    }
    public void reload() {
        this.setBullets(this.getWeapon().getMaxAmmo());
    }
    public int heal() {
        int healing = random.nextInt(5, 50);
        this.setHp(this.getHp()+healing);
        return this.getHp();
    }
}
