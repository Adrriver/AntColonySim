/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antcolonysimulation;

import java.util.HashMap;

/**
 *
 * @author Adrian Rivera
 */
public class Soldier extends Ant{

    //id
    private int ID;
    //equal to decimal 10, decremented daily
    private int lifeSpan;
    private int position;
    private boolean expired;
    
    public Soldier(int ID){
        setID(ID);
        lifeSpan = 3650;
    }
    @Override
    public boolean hasExpired() {
        return false;
    }

    public void setExpired(boolean status){
        this.expired = status;
    }
    
    @Override
    public void move() {
      
    }

    @Override
    public void remove() {
        
    }

    @Override
    public boolean isSquareOpen() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void act() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getPosition() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean ageAnt() {
        if(this.lifeSpan - 1 != 0){
            this.lifeSpan -= 1;
            return false;
        }
        else{
            setExpired(true);
            return true;
        }
    }

    public int getAge(){
        return this.lifeSpan;
    }
    
    @Override
    public void setID(int ID) {
        this.ID = ID;
    }
    @Override 
    public int getID(){
        return ID;
    }

    @Override
    public void setPosition(int pos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
