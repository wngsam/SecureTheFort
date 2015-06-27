/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

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
    
}
