/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package items;

import java.io.Serializable;

/**
 *
 * @author Desktop
 */
public class Gate implements Serializable {
    
    private final String name;
    private final int level;
    private final int cost;
    private final int defBonus;
    private final int maxHealthBonus;

    public Gate(String name, int level, int cost, int defBonus, int maxHealthBonus) {
        this.name = name;
        this.level = level;
        this.cost = cost;
        this.defBonus = defBonus;
        this.maxHealthBonus = maxHealthBonus;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getCost() {
        return cost;
    }

    public int getDefBonus() {
        return defBonus;
    }

    public int getMaxHealthBonus() {
        return maxHealthBonus;
    }
    
}
