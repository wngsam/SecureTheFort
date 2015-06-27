/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import items.Gate;
import items.Tower;

/**
 *
 * @author Desktop
 */
public class Store {
    
    private final static Store instance = new Store();
    private static Tower[] towers;
    private static Gate[] gates;
    
    private Store(){
        createTowersAndGates();
    }
    
    public static Store getInstance(){
        return instance;
    }
    
    public VBox displayStore(Stage primaryStage, Fort myFort,Scene mainScene){
        VBox store = new VBox();
        int gold = myFort.getGold();
        
        int soldierPrice = (myFort.getSoldiers()*6+3);
        store.getChildren().add(new Label(
                "Buy Soldiers! +2 Atk & 1 Def each!\n"
                +"Cost: "+soldierPrice+"g\n"
        ));
        Button buySoldier = new Button("Buy");
        if(soldierPrice>gold){
            buySoldier.setDisable(true);
        }
        buySoldier.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                myFort.addSoldier(soldierPrice);
                primaryStage.setScene(new Scene(displayStore(primaryStage,myFort,mainScene),800,600));
            }
        });
        store.getChildren().add(buySoldier);
        
        int peasantPrice = (myFort.getPeasants()*4+2);
        store.getChildren().add(new Label(
                "Hire Peasants! +1 Gold/Fight & +1 HP/Fight\n"
                +"Cost: "+peasantPrice+"g\n"
        ));
        Button buyPeasant = new Button("Buy");
        if(peasantPrice>gold){
            buyPeasant.setDisable(true);
        }
        buyPeasant.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                myFort.addPeasant(peasantPrice);
                primaryStage.setScene(new Scene(displayStore(primaryStage,myFort,mainScene),800,600));
            }
        });
        store.getChildren().add(buyPeasant);
        
        int investmentPrice = (myFort.getInvestments()*10+5);
        store.getChildren().add(new Label(
                "Purchase Investments! Random chance of +/- Gold/Fight\n"
                +"Cost: "+investmentPrice+"g\n"
        ));
        Button buyInvestment = new Button("Buy");
        if(investmentPrice>gold){
            buyInvestment.setDisable(true);
        }
        buyInvestment.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                myFort.addInvestment(investmentPrice);
                primaryStage.setScene(new Scene(displayStore(primaryStage,myFort,mainScene),800,600));
            }
        });
        store.getChildren().add(buyInvestment);
        
        Tower saleTower = towers[myFort.getTower().getLevel()-1];
        
        store.getChildren().add(new Label(
                "\n"+saleTower.getName()+"\n"
                +"Cost: "+saleTower.getCost()+"g \n"
                +"Level: "+saleTower.getLevel()+"\n"
                +"+Atk: "+saleTower.getAtkBonus()+"\n"
                +"+HPRegen: "+saleTower.getHpRegenBonus()+"\n\n"
        ));
        
        Button buyTower = new Button("Buy");
        if(saleTower.getLevel()>myFort.getLevel()||saleTower.getCost()>gold){
            buyTower.setDisable(true);
        }
        buyTower.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                myFort.purchaseTower(saleTower);
                primaryStage.setScene(new Scene(displayStore(primaryStage,myFort,mainScene),800,600));
            }
        });
        store.getChildren().add(buyTower);
        
        Gate saleGate = gates[myFort.getGate().getLevel()-1];
        
        store.getChildren().add(new Label(
                "\n"+saleGate.getName()+"\n"
                +"Level: "+saleGate.getLevel()+"\n"
                +"Cost: "+saleGate.getCost()+"\n"
                +"+Def: "+saleGate.getDefBonus()+"\n"
                +"+HP: "+saleGate.getMaxHealthBonus()+"\n\n"
        ));
        
        Button buyGate = new Button("Buy");
        if(saleGate.getLevel()>myFort.getLevel()||saleGate.getCost()>gold){
            buyGate.setDisable(true);
        }
        buyGate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                myFort.purchaseGate(saleGate);
                primaryStage.setScene(new Scene(displayStore(primaryStage,myFort,mainScene),800,600));
            }
        });
        store.getChildren().add(buyGate);
        
        Button exit = new Button("Exit");
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(mainScene);
            }
        });
        store.getChildren().add(exit);
        
        return store;
    }
    
    private void createTowersAndGates(){
        towers = new Tower[5];
        gates = new Gate[5];
        
        //Name, Level, Cost, ATK, HPRegen
        towers[0] = new Tower("Barricaded Tower",2,750,35,2);
        towers[1] = new Tower("Stone Tower",4,1000,35,2);
        towers[2] = new Tower("Reinforced Tower",6,1500,35,2);
        towers[3] = new Tower("Archer Tower",9,2500,38,1);
        towers[4] = new Tower("Crossbow Tower",11,4000,40,1);
        //Name, Level, Cost, DEF, HP
        gates[0] = new Gate("Barricaded Gate",2,1000,20,50);
        gates[1] = new Gate("Stone Gate",4,1250,20,50);
        gates[2] = new Gate("Reinforced Gate",6,1750,30,50);
        gates[3] = new Gate("Iron Gate",9,2750,35,50);
        gates[4] = new Gate("Steel Gate",11,4750,35,55);
    }
    
}
