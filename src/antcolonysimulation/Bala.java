/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antcolonysimulation;


import dataStructures.Iterator;
import java.util.Random;


/**
 *
 * @author Adrian Rivera
 */
    

public class Bala extends Ant{
    
    private int ID;
    //equal to decimal 10, decremented daily
    private int lifeSpan;
    private int position;
    private boolean expired;
    private boolean attackOutcome;
    private AntFrequency antFrequency;
    
    
    public Bala(int ID){
        setID(ID);
        position = 0;
        lifeSpan = 3650;
        attackOutcome = false;
    }
    
    @Override
    public boolean hasExpired() {
        return expired; //To change body of generated methods, choose Tools | Templates.
    }
    
    public void setExpired(boolean status){
        this.expired = status;
    }

    @Override
    public void move(){
       //moveBala is implemented in place of this method 
    }
    
    public AntFrequency moveBala() {
        if(!hasExpired()){
        
        int[] possibleMoves = {getPosition() - 26, getPosition() + 27, getPosition() - 1, 
                                getPosition() + 26, getPosition() - 27, getPosition() + 28,
                                    getPosition() + 1, getPosition() - 28};
        
        Random nextMove = new Random();
        int next;
        int move;
        do {
            move = nextMove.nextInt(7);
            next = possibleMoves[move];
            
        } while(next > 728 || next < 0 || next % 27 == 0 || (
                getPosition() % 27 == 0) && (next - 1) % 26 == 0);
        
        
            
        
        
            AntColony.Environment.gridContainer.getGridSquare(getPosition()).decrementBalaCnt();   
            //Update colonyNodeViews to reflect current position of this scout ant
            
            AntColony.Environment.gridContainer.getGridSquare(getPosition()).getColNodeView().setBalaCount(
            AntColony.Environment.gridContainer.getGridSquare(getPosition()).getNumBala());         
        
                   
           
            //if this ant was the only ant in the Square object being left, then hide its icon
            if(AntColony.Environment.gridContainer.getGridSquare(getPosition()).getNumBala() == 0)
                AntColony.Environment.gridContainer.getGridSquare(getPosition()).getColNodeView().hideBalaIcon();
            
            setPosition(next);
            
            //Updates Square object in grid to reflect new position of this scout
            AntColony.Environment.gridContainer.getGridSquare(next).incrementBalaCnt();
            
            AntColony.Environment.gridContainer.getGridSquare(getPosition()).getColNodeView().setBalaCount(
            AntColony.Environment.gridContainer.getGridSquare(getPosition()).getNumBala());
                        
            AntColony.Environment.gridContainer.getGridSquare(getPosition()).getColNodeView().showNode();
            
            if(AntColony.Environment.gridContainer.getGridSquare(getPosition()).getNumBala() == 1)
                AntColony.Environment.gridContainer.getGridSquare(getPosition()).getColNodeView().showBalaIcon();
            
            Square detectColonyAnt = AntColony.Environment.gridContainer.getGridSquare(getPosition());
            
            if(detectColonyAnt.getNumForager() >= 1 || detectColonyAnt.getNumScout() >= 1 || detectColonyAnt.getNumSoldier() >= 1){
                BinaryTree squarePopulation = new BinaryTree();
                squarePopulation.add(new AntFrequency("Forager", detectColonyAnt.getNumForager()));
                squarePopulation.addLeft(new AntFrequency("Scout", detectColonyAnt.getNumScout()), squarePopulation.getRootItem());
                squarePopulation.addRight(new AntFrequency("Soldier", detectColonyAnt.getNumSoldier()), squarePopulation.getRootItem());
                antFrequency = act(squarePopulation);//initiate fight with one ant from main colony
            }
            
            
        } else {
             if(AntColony.Environment.gridContainer.getGridSquare(getPosition()).getNumBala() < 2)
                    AntColony.Environment.gridContainer.getGridSquare(getPosition()).getColNodeView().hideBalaIcon();
                else{
                AntColony.Environment.gridContainer.getGridSquare(getPosition()).decrementBalaCnt();
                AntColony.Environment.gridContainer.getGridSquare(getPosition()).getColNodeView().setBalaCount(
                AntColony.Environment.gridContainer.getGridSquare(getPosition()).getNumBala());
                }
               
        } 
        if(antFrequency == null){
            return new AntFrequency("default", 0, false);
        }
        return antFrequency;
    }

    
    @Override
    public void act(){
    
    }
    
    public AntFrequency act(BinaryTree tree) {
        AntFrequency aFreq = new AntFrequency("non-ant", 0, false);
        boolean complete = false;
        int min, temp, empty = 0; // empty helps to ensure that when no ants exist in square, 
                             //  a failure outcome is returned (no fight occurred)
        
        Iterator itr = tree.inOrderIterator();
        AntFrequency sqFreq = (AntFrequency) itr.getCurrent();
        min = (int) sqFreq.getFreq();
            itr.next();
        /* find type of ant lowest in frequency within square */
        while(itr.hasNext() && !complete){     
           
            sqFreq = (AntFrequency) itr.getCurrent();
            temp = sqFreq.getFreq();
           
            
            if(temp == 1){
                min = temp;
                aFreq = sqFreq;
                complete = true;
            } else if(temp < min)
                min = temp;
                aFreq = sqFreq;

            
            itr.next();
        }
        
        double outcome = Math.random();
        if(outcome < .5){
            aFreq.setKilled(true) ;//bala won fight
        } else {
            aFreq.setKilled(false);
        }
            aFreq.setPos(getPosition());
            
      return aFreq; 
    }

    public void setOutcome(boolean val){//
        this.attackOutcome = val;
    }
    
    public boolean getOutcome (){//Doubles in functionality: i) returns whether a fight occurred or ii) if i), then outcome of attack
        return this.attackOutcome;       
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

    @Override
    public int getAge(){
        return this.lifeSpan;
    }
    @Override
    public void setID(int ID) {
        this.ID = ID;
    }
    public int getID() {
        return ID;
    }

    @Override
    public void setPosition(int pos) {
        this.position = pos;
    }

    @Override
    public int getPosition() {
        return this.position;
    }
}
