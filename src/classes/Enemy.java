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
        
        health = 500+(level*250);
        attack = 50+(level*25);
        defense = 10+(level*15);
        
        peasants = level*(4-(rand));
        soldiers = level*rand*2;
        attack+=soldiers*2;
        defense+=soldiers;
        tactics = level*(6-(rand));
        defense+=tactics;
        health+=20*tactics;
        
        investments = rand*level*rand;
        
    }
    
    public static int randInt(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
        
    }
    
}
