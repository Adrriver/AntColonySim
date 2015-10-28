/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antcolonysimulation;

import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author Adrian Rivera
 */
public class Scout extends Ant {
    //id
    private int ID;
    //equal to decimal 10, decremented daily
    private double lifeSpan;
    private int position;
    private boolean expired;
    
    public Scout(int ID){
        setID(ID);
        lifeSpan = 1.00;
        position = 364;
        expired = false;
    }
    
    
    @Override
    public boolean hasExpired() {
        
        return true;
    }
    public void setExpired(boolean status){
        this.expired = status;
    }
    
    @Override
    public void move() {
        
        int[] possibleMoves = {getPosition() + 26, getPosition() + 27, getPosition() + 28, 
                                getPosition() - 26, getPosition() - 27, getPosition() - 28,
                                    getPosition() + 1, getPosition() - 1};
        
        Random nextMove = new Random();
        int next = nextMove.nextInt(7);
        
        while(next > 728 || next < 0){
            next = nextMove.nextInt(7);
        }
        
        
        
        AntColony.Environment.gridContainer.getGridSquare(getPosition()).decrementScoutCnt();                        
            //Updates Square object in grid to reflect new position of this forager
            AntColony.Environment.gridContainer.getGridSquare(
                    next).incrementScoutCnt();
            //Update colonyNodeViews to reflect current position of this forager ant
            AntColony.Environment.gridContainer.getGridSquare(getPosition()).getColNodeView().setScoutCount(
            AntColony.Environment.gridContainer.getGridSquare(getPosition()).getNumScout() - 1);
            //if this ant was the only ant in the Square object being left, then hide its icon
            if(AntColony.Environment.gridContainer.getGridSquare(getPosition()).getNumScout() == 0)
                AntColony.Environment.gridContainer.getGridSquare(getPosition()).getColNodeView().hideScoutIcon();
            
            AntColony.Environment.gridContainer.getGridSquare(next).getColNodeView().setScoutCount(
            AntColony.Environment.gridContainer.getGridSquare(next).getNumScout() + 1);
            
            AntColony.Environment.gridContainer.getGridSquare(next).getColNodeView().showNode();
            
            if(AntColony.Environment.gridContainer.getGridSquare(next).getNumScout() == 0)
                AntColony.Environment.gridContainer.getGridSquare(getPosition()).getColNodeView().showScoutIcon();
    }
    
    @Override
    public void setPosition(int pos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getPosition() {
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
    public void ageAnt() {
        if(this.lifeSpan - 1 != 0)
            this.lifeSpan -= .01;
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
