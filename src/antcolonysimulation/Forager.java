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
public class Forager extends Ant{
    
    //id
    private int ID;
    //equal to decimal 10, decremented daily
    private int lifeSpan;
    //log of ant moves
    private HashMap moveLog;
    //stores units of food ant possesses (wrapped integer 1)
    private int food;
    private boolean mode;//forage mode == true; return to nest mode == false;
    private int position;
    private boolean expired;
    
    public Forager(int ID){
        setID(ID);
    }
    
    public boolean getMode(){
        return mode;
    }
    public void setMode(boolean mode){
        this.mode = mode;
    }
    @Override
    public boolean hasExpired() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void setExpired(boolean status){
        this.expired = status;
    }
    
    @Override
    public void move() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public int position() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public int getFood() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public void setFood() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ageAnt() {
        if(this.lifeSpan - 1 != 0)
            this.lifeSpan--;
        else
            setExpired(true);
    }

    @Override
    public void setID(int ID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public int getID(){
        return ID;
    }
}
