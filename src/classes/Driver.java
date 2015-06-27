/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Desktop
 */
public class Driver extends Application {
    
    private static Fort myFort;
    private final static Store store = Store.getInstance();
    private final static EventManager em = EventManager.getInstance();
    private static Stage primaryStage;
    private final Scene mainScene = new Scene(viewMainMenu(),800,600);
    
    private  void loadSave(){
        try{
            FileInputStream fis = new FileInputStream("save.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            myFort = (Fort) ois.readObject();
            ois.close();
            System.out.println("Load successful.");
        }catch(IOException | ClassNotFoundException e){
            System.out.println("Load unsuccessful, possibly save not found.");
            //e.printStackTrace();
            myFort = new Fort();
        }
    }
    
    private void save(){
        try{
            FileOutputStream fos = new FileOutputStream("save.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(myFort);
            oos.close();
            System.out.println("Save successful.");
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Save failed.");
        }
    }
    
    @Override
    public void start(Stage primaryStage) {
        loadSave();
        this.primaryStage = primaryStage;
        
        primaryStage.setTitle("Secure The Fort!");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }
    
    public HBox viewMainMenu(){
        HBox menu = new HBox();
        
        Button fight = new Button("Fight");
        fight.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
            }
        });
        menu.getChildren().add(fight);
        
        Button fort = new Button("Fort");
        fort.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(new Scene(showStats(),800,600));
            }
        });
        menu.getChildren().add(fort);
        
        Button shop = new Button("Shop");
        shop.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(new Scene(store.displayStore(primaryStage,myFort,mainScene),800,600));
            }
        });
        menu.getChildren().add(shop);
        
        Button gamble = new Button("Gamble");
        gamble.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(new Scene(gamble(""),800,600));
            }
        });
        menu.getChildren().add(gamble);
        
        Button exit = new Button("Exit");
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                save();
                primaryStage.close();
            }
        });
        menu.getChildren().add(exit);
        
        return menu;
    }
    
    public VBox showStats(){
        VBox stats = new VBox();
        
        stats.getChildren().add(new Label(
                "Level: "+myFort.getLevel()+"\n"+
                "Exp: "+myFort.getExp()+"\n"+
                "HP: "+myFort.getCurrentHealth()+"/"+myFort.getMaxHealth()+"   HPReg: "+myFort.getHpRegen()+"\n"+
                "Gold: "+myFort.getGold()+"   Income: "+myFort.getIncome()+"\n"+
                "Atk: "+myFort.getAttack()+"   Def:"+myFort.getDefense()+"\n\n"+
                "Peasants: "+myFort.getPeasants()+"\n"+
                "Soldiers: "+myFort.getSoldiers()+"\n"+
                "Tactics: "+myFort.getTactics()+"\n"+
                "Luck: "+myFort.getLuck()+"\n\n"+
                "Stat Points: "+myFort.getStatPoints()+"\n\n"+
                "Investments: "+myFort.getInvestments()+"\n"
                
        ));
        
        if(myFort.getStatPoints()>0){
            HBox addStats = new HBox();
            
            Button addPeasant = new Button("+Peasant");
            addPeasant.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    myFort.addPeasant(0);
                    primaryStage.setScene(new Scene(showStats(),800,600));
                }
            });
            Button addSoldier = new Button("+Soldier");
            addSoldier.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    myFort.addSoldier(0);
                    primaryStage.setScene(new Scene(showStats(),800,600));
                }
            });
            Button addTactic = new Button("+Tactic");
            addTactic.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    myFort.addTactic();
                    primaryStage.setScene(new Scene(showStats(),800,600));
                }
            });
            Button addLuck = new Button("+Luck");
            addLuck.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    myFort.addLuck();
                    primaryStage.setScene(new Scene(showStats(),800,600));
                }
            });
            addStats.getChildren().addAll(addPeasant,addSoldier,addTactic,addLuck);
            stats.getChildren().add(addStats);
        }
        
        Button exit = new Button("Exit");
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(mainScene);
            }
        });
        stats.getChildren().add(exit);
        
        return stats;
    }
    
    public VBox gamble(String msg){
        VBox gamble = new VBox();
        
        gamble.getChildren().add(new Label(msg));
        
        Button exit = new Button("Exit");
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(mainScene);
            }
        });
        gamble.getChildren().add(exit);
        
        return gamble;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
