package com.example.soulborn;

import javafx.scene.image.Image;

public class NPC extends PlayableCharacter{
    public NPC(String username, int kills, boolean alive, Weapon weapon, int hp, boolean player, Image icon, int defaultPower, int defaultDexterity, int defaultFaith, int defaultArmor, int defaultIntelligence, int power, int dexterity, int faith, int armor, int intelligence) {
        super(username, kills, alive, weapon, hp, player, icon, defaultPower, defaultDexterity, defaultFaith, defaultArmor, defaultIntelligence, power, dexterity, faith, armor, intelligence);
    }
}
