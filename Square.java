package com.adrianmrivera;
/**
 *
 * @author AdrianMRivera
 */
import java.util.*;

public class Square {
    
    private int unitsOfFood;
    private int unitsOfPheromone;
    private int open;
    
    private int bala;
    private int scouts;
    private int foragers;
    private int soldiers;
    
    
    public Square(){        
        setRevealed(-2);
    }
    
    public int getFood(){
        
        return this.unitsOfFood;
    }
    
    public void setPheromone(int units){
        this.unitsOfPheromone = units;
    }
    public int getPheromone(){
        
        return this.unitsOfPheromone;      
    }
    
    public void setRevealed(int status){
        this.open = status; // '-1' == not revealed, '-2' == revealed
    }
    
    public boolean isRevealed(){
        boolean revealed = false;
        
        if(this.open == -1){
            revealed = false;
        } else if( this.open == -2){
            revealed = true;
        }
            return revealed;
    }
    
    public void setFood(int units){
        this.unitsOfFood = units;
    }       
    
    public void setNumBala(int num){
        this.bala = num;
    }
    
    public void setNumScout(int num){
        this.scouts = num;
    }
    
    public void setNumForager(int num){
        this.foragers = num;
    }
    
    public void setNumSoldier(int num){
        this.soldiers = num;
    }
    
    public int getNumBala(){
        return this.bala;
    }
    
    public int getNumScout(){
        return this.scouts;       
    }
    
    public int getNumForager(){
        return this.foragers;       
    }
    
    public int getNumSoldier(){
        return this.soldiers;
    }
    
    
} // end class Square definition
