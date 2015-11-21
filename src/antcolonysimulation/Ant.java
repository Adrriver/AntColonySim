/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antcolonysimulation;

import java.util.*;

/**
 *
 * @author Adrian Rivera
 */
abstract public class Ant {
    
    //id
    private int ID;
    //equal to decimal 10, decremented daily
    private int lifeSpan;
    //log of ant moves
    private HashMap moveLog;
    //status of ant's life
    private boolean expired;
    //stores units of food ant possesses (wrapped integer 1)
    private int food;    
   
    
//called by ageAnt, calls remove when var lifespan equals 0
    public abstract boolean hasExpired();
    //makes determined move to adjacent square
    public abstract void move();
    //defines and performs primary action(s) for ant type
    public abstract void act();
    //sets grid square of calling ant object
    public abstract void setPosition(int pos);
    //returns grid square of calling ant object
    public abstract int getPosition(); 
    //tracks days of ants life (10/lifetime)
    public abstract boolean ageAnt();
    //sets life status, usually to false
    public abstract void setExpired(boolean val);
    //gets age of ant
    public abstract int getAge();
    //sets ant's ID
    public abstract void setID(int ID);
    //gets ant's ID
    public abstract int getID();
    
    
}
