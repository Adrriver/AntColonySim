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
    //stores units of food ant possesses (wrapped integer 1)
    private int food;
    
    //called by ageAnt, calls remove when var lifespan equals 0
    public abstract boolean expire();
    //makes determined move to adjacent square
    public abstract void move();
    //removes deceased ants from the colony and colony board
    public abstract void remove();
    //given ant type, determines whether given square is accessible 
    public abstract boolean isSquareOpen();
    //defines and performs primary action(s) for ant type
    public abstract void act();
    //returns grid square of calling ant object
    public abstract int position(); 
    //tracks days of ants life (10/lifetime)
    public abstract void ageAnt();
    //sets ant's ID
    public abstract int getID();
    
}
