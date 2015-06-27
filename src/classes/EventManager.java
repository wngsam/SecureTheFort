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
public class EventManager {
    
    private final static EventManager instance = new EventManager();
    
    private EventManager(){}
    
    public static EventManager getInstance(){
        return instance;
    }
    
    public String calcEDmg(Fort myFort, Enemy e){
        
        if(e.getHealth()<=0){
            return "Enemy died!";
        }
        
        int eDmg = e.getAttack()-myFort.getDefense();
        System.out.println("Testing Fort Def Bonus: "+myFort.getDefense());
        
        int choice = 0;//LESS DMG
        int roll = randInt(1,100);
        
        if(roll>95){
            choice = 1; //CRIT
        }else if(roll>90){
            choice = 2; //HEAL
        }else if(roll>80){
            choice = 3; //IGNORE DEF
        }else if(roll>10){
            choice = 4; //NORMAL
        }
        
        switch(choice){
            case 0: eDmg = eDmg/2;
                    myFort.takeDmg(eDmg);
                    return "The enemy only dealt "+eDmg+" dmg!\n";
            case 1: eDmg = eDmg*2;
                    myFort.takeDmg(eDmg);
                    return "The enemy dealt a deadly "+eDmg+" dmg\n";
            case 2: int heal = randInt(1,10)*10*e.getLevel();
                    e.takeDmg(-heal);
                    myFort.takeDmg(eDmg);
                    return "The enemy did "+eDmg+"dmg and healed for"+heal+"! \n";
            case 3: eDmg = eDmg+(myFort.getDefense()/10);
                    myFort.takeDmg(eDmg);
                    return "The enemy ignored your defense and dealt "+eDmg+" dmg!\n";
            case 4: myFort.takeDmg(eDmg);
                    return "The enemy dealt "+eDmg+" dmg!\n";
        }
        
        return "";
    }
    
    public String calcMyDmg(Fort myFort, Enemy e){
        int myLuck = myFort.getLuck();
        int myTactic = myFort.getTactics();
        int yourDmg = myFort.getAttack()-e.getDefense();
        System.out.println("Testing E Def Reduction: "+e.getDefense());
        int roll = randInt(1,100);
        
        if((roll+(myLuck/50)+(myTactic/50))<20){
            yourDmg = 0;
            e.takeDmg(yourDmg);
            return "Unforunately your attack was not successful.\n";
        }else if((roll+((myLuck+myTactic)/100))>95){
            yourDmg = yourDmg*2;
            e.takeDmg(yourDmg);
            return "You did a massive blow of "+yourDmg+"!\n";
        }else{
            e.takeDmg(yourDmg);
            return "You dealt "+yourDmg+" dmg!\n";
        }
    }
    
    public String useAssault(Fort myFort, Enemy e){
        String result = "";
        
        result+=calcMyDmg(myFort,e);
        
        result+=calcEDmg(myFort,e);
        
        return result;
    }
    
    public String useShields(Fort myFort, Enemy e){
        String result = "";
        
        int defModify = ((myFort.getDefense())/10);
        int roll = randInt(1,2);
        int def = defModify*roll+(myFort.getLuck()/100);
        
        myFort.addDef(def);
        result+="You gained "+def+" def.\n";
        e.addDef((defModify-(myFort.getTactics()/100)));
        result+=calcMyDmg(myFort,e);
        e.addDef(-(defModify-(myFort.getTactics()/100)));
        
        result+=calcEDmg(myFort,e);
        myFort.addDef(-def);
        
        return result;
    }
    
    public String useAmbush(Fort myFort, Enemy e){
        String result = "";
        
        int roll = randInt(1,100);
        if((roll+((myFort.getTactics()+myFort.getLuck())/50))>40){
            roll = randInt(2,10);
            e.addDef(-((e.getDefense()/roll)+(myFort.getTactics()/100)));
            result+="Your sneak attack was successful! The enemy was unprepared!\n";
            result+=calcMyDmg(myFort,e);
            e.addDef(((e.getDefense()/roll)+(myFort.getTactics()/100)));
        
        }else{
            result+="Your sneak attack was unsuccessful.\n";
        }
        
        result+=calcEDmg(myFort,e);
        
        return result;
    }
    
    public String useBerserk(Fort myFort, Enemy e){
        String result = "";
        
        result+=calcMyDmg(myFort,e);
        
        int roll = randInt(20,50);
        int hpCost = myFort.getCurrentHealth()/roll;
        myFort.takeDmg(hpCost);
        roll = randInt(0,50);
        int bonus = hpCost+myFort.getLevel()*roll+myFort.getTactics()+(myFort.getAttack()/10);
        result+="You sacrificed "+hpCost+"HP to deal "+bonus+" extra dmg!\n";
        e.takeDmg(bonus);
        result+=calcEDmg(myFort,e);
        
        return result;
    }
    
    public static int randInt(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
        
    }
    
}
