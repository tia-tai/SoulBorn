package com.example.soulborn;

import javafx.scene.image.Image;

import java.util.Random;

public class NPC extends PlayableCharacter{
    public Random random = new Random();
    public NPC(String username, int kills, boolean alive, Weapon weapon, int bullets, int hp, boolean player, Image icon, int defaultPower, int defaultDexterity, int defaultFaith, int defaultArmor, int defaultIntelligence, int power, int dexterity, int faith, int armor, int intelligence) {
        super(username, kills, alive, weapon, bullets, hp, player, icon, defaultPower, defaultDexterity, defaultFaith, defaultArmor, defaultIntelligence, power, dexterity, faith, armor, intelligence);
    }

    public void respawn() {
        this.setAlive(true);
    }
    public void action(PlayableCharacter name) {

    }
    public void generate() {
        String username = "Bot " + random.nextInt(0, 1000);
        int kills = 0;
        boolean alive = true;
        Weapon weapon = new Weapon("Bubble Launcher", 20, 12, 70);
        int bullets = 20;
        int hp = 100;
        boolean player = false;
        Image icon = null;
        int defaultPower = random.nextInt(0, 3);
        int defaultDexterity = random.nextInt(0, 3);
        int defaultFaith = random.nextInt(0, 3);
        int defaultArmor = random.nextInt(0, 3);
        int defaultIntelligence = random.nextInt(0, 3);
        int power = defaultPower;
        int dexterity = defaultDexterity;
        int faith = defaultFaith;
        int armor = defaultArmor;
        int intelligence = defaultIntelligence;

        PlayableCharacter newNPC = new PlayableCharacter(username, kills, alive, weapon, bullets, hp, player, icon, defaultPower, defaultDexterity, defaultFaith, defaultArmor, defaultIntelligence, power, dexterity, faith, armor, intelligence);
    }


}
