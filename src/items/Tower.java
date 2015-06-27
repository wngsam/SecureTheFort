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
public class Tower implements Serializable {
    
    private final String name;
    private final int level;
    private final int cost;
    private final int atkBonus;
    private final int hpRegenBonus;

    public Tower(String name, int level, int cost, int atkBonus, int hpRegenBonus) {
        this.name = name;
        this.level = level;
        this.cost = cost;
        this.atkBonus = atkBonus;
        this.hpRegenBonus = hpRegenBonus;
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

    public int getAtkBonus() {
        return atkBonus;
    }

    public int getHpRegenBonus() {
        return hpRegenBonus;
    }
    
}
