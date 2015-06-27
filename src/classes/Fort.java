/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import items.Gate;
import items.Tower;
import java.io.Serializable;
import static java.lang.Math.sqrt;

/**
 *
 * @author Desktop
 */
public class Fort implements Serializable {
    
    private int level;
    private int exp;
    private int currentHealth; //Current health
    private int maxHealth; //Total health
    private int gold; //Your treasury
    private int income; //From upgrades/peasants
    
    private int attack; //Damage to enemies
    private int defense; //Damage mitigation from enemies
    private int hpRegen; //Regen to health per turn
    
    private int peasants; //Increase income/hp regen by 1 each
    private int soldiers; //Increase attack by 2, defense by 1 each
    private int tactics; //Increase defense by 1 and used for combat events
    private int luck; //Used for combat events and gold drop
    
    private int statPoints;
    
    private Tower tower; //Tower upgrades add atk/hp regen
    private Gate gate; //Gate upgrades add def/max health
    private int investments; //Investments for varying income
    
    
    public Fort(){
        level = 1;
        exp = 0;
        maxHealth = 1000;
        gold = 1000;
        income = 10;
        attack = 20;
        defense = 10;
        hpRegen = 10;
        peasants = 10;
        soldiers = 10;
        tactics = 0;
        luck = 0;
        statPoints = 10;
        investments = 0;
        tower = new Tower("Wooden Tower",1,0,50,1);
        attack+=50;
        hpRegen++;
        gate = new Gate("Wooden Gate",1,0,20,50);
        defense+=20;
        maxHealth+=50;
        currentHealth = maxHealth;
    }
    
    public void addDef(int amt){
        defense+=amt;
    }
    
    public void takeDmg(int amt){
        currentHealth-=amt;
    }
    
    public Boolean addExp(int amt){
        exp+=amt;
        return calculateLevel();
    }
    
    public Boolean calculateLevel(){
        Boolean leveled = false;
        int calcLevel = (int) ((sqrt(exp)/10)+1);
        int levelDiff = calcLevel-level;
        if(levelDiff>0){
            levelUp(levelDiff);
            leveled = true;
        }
        return leveled;
    }
    
    public void levelUp(int amt){
        level+=amt;
        statPoints+=(amt*10);
    }
    
    public void addGold(int amt){
        gold+=amt;
    }
    
    public void purchaseTower(Tower t){
        gold-=t.getCost();
        tower = t;
        attack+=t.getAtkBonus();
        hpRegen+=t.getHpRegenBonus();
    }
    
    public void purchaseGate(Gate g){
        gold-=g.getCost();
        gate = g;
        defense+=g.getDefBonus();
        maxHealth+=g.getMaxHealthBonus();
        currentHealth = maxHealth;
    }
    
    public void remInvestment(int amt){
        investments-=amt;
    }
    
    public void addInvestment(int amt){
        gold-=amt;
        investments++;
    }
    
    public void addPeasant(int amt){
        gold-=amt;
        peasants++;
        income+=1;
        hpRegen+=1;
        statPoints--;
    }
    
    public void addSoldier(int amt){
        gold-=amt;
        soldiers++;
        attack+=2;
        defense+=1;
        statPoints--;
    }
    
    public void addTactic(){
        tactics++;
        defense+=1;
        statPoints--;
    }
    
    public void addLuck(){
        luck++;
        statPoints--;
    }

    public int getLevel() {
        return level;
    }

    public int getExp() {
        return exp;
    }
    
    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }
    
    public int getMaxHealth() {
        return maxHealth;
    }

    public int getGold() {
        return gold;
    }

    public int getIncome() {
        return income;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getHpRegen() {
        return hpRegen;
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

    public int getLuck() {
        return luck;
    }

    public int getStatPoints() {
        return statPoints;
    }

    public Tower getTower() {
        return tower;
    }

    public Gate getGate() {
        return gate;
    }
    
    public int getInvestments() {
        return investments;
    }
    
}
