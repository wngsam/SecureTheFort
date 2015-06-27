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
    
    private String name;
    private int level;
    private int cost;
    private int defBonus;
    private int maxHealthBonus;

    public Gate(String name, int level, int cost, int defBonus, int maxHealthBonus) {
        this.name = name;
        this.level = level;
        this.cost = cost;
        this.defBonus = defBonus;
        this.maxHealthBonus = maxHealthBonus;
    }
    
}
