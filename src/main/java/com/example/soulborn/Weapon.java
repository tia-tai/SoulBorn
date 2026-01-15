package com.example.soulborn;

import java.io.Serializable;
import java.util.ArrayList;

public class Weapon implements Serializable {
    private String weaponName;
    private int maxAmmo;
    private int damage;
    private float accuracy;
    static ArrayList<Weapon> weapons = new ArrayList<>();

    public Weapon(String weaponName, int maxAmmo, int damage, float accuracy) {
        this.weaponName = weaponName;
        this.maxAmmo = maxAmmo;
        this.damage = damage;
        this.accuracy = accuracy;
        weapons.add(this);
    }

    public String getWeaponName() {
        return weaponName;
    }

    public void setWeaponName(String weaponName) {
        this.weaponName = weaponName;
    }

    public int getMaxAmmo() {
        return maxAmmo;
    }

    public void setMaxAmmo(int maxAmmo) {
        this.maxAmmo = maxAmmo;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public float getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(float accuracy) {
        this.accuracy = accuracy;
    }

    public static ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    public static void setWeapons(ArrayList<Weapon> weapons) {
        Weapon.weapons = weapons;
    }

    public static void generate() {
        if (weapons.isEmpty()) {
            new Weapon("Pistol", 16, 12, 0.6f);
            new Weapon("Club", 80, 5, 1f);
            new Weapon("Shoeb", 1, 80, 0.2f);
            new Weapon("Alex", 1, 1, 0.01f);
        }
    }
}
