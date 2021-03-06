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
public class Scout extends Ant {
    //id
    private int ID;
    //equal to decimal 10, decremented daily
    private int lifeSpan;
    private int position;
    private boolean expired;
    
    public Scout(int ID){
        setID(ID);
        lifeSpan = 3650;
        position = 364;
        setPosition(position);
        expired = false;
        
    }
    
    
    @Override
    public boolean hasExpired() {
        
        return this.expired;
    }
    @Override
    public void setExpired(boolean status){
        this.expired = status;
    }
    
    @Override
    public void move() {
        
        if(!hasExpired()){
        
        int[] possibleMoves = {getPosition() - 26, getPosition() + 27 , getPosition() - 28, 
                                getPosition() + 26, getPosition() - 27, getPosition() + 28,
                                    getPosition() + 1 , getPosition() - 1};
        
         
        int next;
        int move;
        do {
            move = AntColony.randomNum.nextInt(8);
            next = possibleMoves[move];
            
        } while(next > 728 || next < 0 || next == 364 || (getPosition() % 26 == 0) && (next % 27 == 0) ||
                getPosition() % 27 == 0 && next % 26 == 0);
        
        
            
        
        if(getPosition() != 364)
            AntColony.Environment.gridContainer.getGridSquare(getPosition()).decrementScoutCnt();   
            //Update colonyNodeViews to reflect current position of this scout ant
            
            AntColony.Environment.gridContainer.getGridSquare(getPosition()).getColNodeView().setScoutCount(
            AntColony.Environment.gridContainer.getGridSquare(getPosition()).getNumScout());         
        
                   
           
            //if this ant was the only ant in the Square object being left, then hide its icon
            if(AntColony.Environment.gridContainer.getGridSquare(getPosition()).getNumScout() == 0)
                AntColony.Environment.gridContainer.getGridSquare(getPosition()).getColNodeView().hideScoutIcon();
            
            setPosition(next);
            
            //Updates Square object in grid to reflect new position of this scout
            AntColony.Environment.gridContainer.getGridSquare(next).incrementScoutCnt();
            
            AntColony.Environment.gridContainer.getGridSquare(getPosition()).getColNodeView().setScoutCount(
            AntColony.Environment.gridContainer.getGridSquare(getPosition()).getNumScout());
                        
            AntColony.Environment.gridContainer.getGridSquare(getPosition()).getColNodeView().showNode();
            
            if(AntColony.Environment.gridContainer.getGridSquare(getPosition()).getNumScout() == 1)
                AntColony.Environment.gridContainer.getGridSquare(getPosition()).getColNodeView().showScoutIcon();
            
            //reveal square for colony
            AntColony.Environment.gridContainer.getGridSquare(getPosition()).setRevealed(true);
            
        } else {
             //if(AntColony.Environment.gridContainer.getGridSquare(getPosition()).getNumScout() == 1)
                    AntColony.Environment.gridContainer.getGridSquare(getPosition()).getColNodeView().hideScoutIcon();
                
                AntColony.Environment.gridContainer.getGridSquare(getPosition()).decrementScoutCnt();
                AntColony.Environment.gridContainer.getGridSquare(getPosition()).getColNodeView().setScoutCount(
                AntColony.Environment.gridContainer.getGridSquare(getPosition()).getNumScout());
                
               
        }           
             
    }
    
    @Override
    public void setPosition(int pos) {
        this.position = pos;
    }

    @Override
    public int getPosition() {
        return this.position;   
    }
    
    @Override
    public void act() {
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
            
            //AntColony.Environment.gridContainer.getGridSquare(getPosition()).getColNodeView().hideScoutIcon();
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

}
