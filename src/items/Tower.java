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
    
    private String name;
    private int level;
    private int cost;
    private int atkBonus;
    private int hpRegenBonus;

    public Tower(String name, int level, int cost, int atkBonus, int hpRegenBonus) {
        this.name = name;
        this.level = level;
        this.cost = cost;
        this.atkBonus = atkBonus;
        this.hpRegenBonus = hpRegenBonus;
    }
    
}
