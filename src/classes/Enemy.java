/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.util.Random;

/**
 *
 * @author Desktop
 */
public class Enemy {
    
    private int level;
    private int xpReward;
    private int goldReward;
    
    private int health;
    private int attack;
    private int defense;
    
    private int peasants; //Chance to give to player
    private int soldiers; //Affects 2atk/1def
    private int tactics; //Affects 1def/20hp & combat events
    private int investments; //Chance to give to player
    
    public Enemy(int level){
        int rand = randInt(1,3);
        this.level = level;
        xpReward = (35*level*level)+(level*rand*rand);
        goldReward = (level*level*level)+(rand*rand*5)+rand;
        
        health = 300+(level*250);
        attack = 20+(level*25);
        defense = 5+(level*15);
        
        peasants = level*(4-(rand));
        soldiers = level*rand*2;
        attack+=soldiers*2;
        defense+=soldiers;
        tactics = level*(6-(rand));
        defense+=tactics;
        health+=20*tactics;
        
        investments = rand*level*rand;
        
    }
    
    public void addDef(int amt){
        defense+=amt;
    }
    
    public void takeDmg(int amt){
        health-=amt;
    }
    
    public static int randInt(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
        
    }

    public int getLevel() {
        return level;
    }

    public int getXpReward() {
        return xpReward;
    }

    public int getGoldReward() {
        return goldReward;
    }

    public int getHealth() {
        return health;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getPeasants() {
        return peasants;
    }

    public int getSoldiers() {
        return soldiers;
    }

    public int getTactics() {
        return tactics;
    }

    public int getInvestments() {
        return investments;
    }
    
}
