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
public class Soldier extends Ant{

    //id
    private int ID;
    //equal to decimal 10, decremented daily
    private int lifeSpan;
    private int position;
    private boolean expired;
    private boolean mode; //true == scout mode; false == attack mode
    
    public Soldier(int ID){
        setID(ID);
        lifeSpan = 3650;
        mode = true;
        position = 364;
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
        //scout mode
        
            
          if(!hasExpired()){
        
            int[] possibleMoves = {getPosition() + 26, getPosition() + 27, getPosition() + 28, 
                                     getPosition() - 26, getPosition() - 27, getPosition() - 28,
                                        getPosition() + 1, getPosition() - 1};
        
                Random nextMove = new Random();
                int next;
                int move;
                do {
                    int bala = detectBala(possibleMoves);
                    if(bala == 0){                
                        move = nextMove.nextInt(7);
                        next = possibleMoves[move];
                    } else {
                        next = bala;
                        mode = false;
                    }

                } while(next > 728 || next < 0 || next == 364 || next % 27 == 0 || (
                        getPosition() % 27 == 0) && (next - 1) % 26 == 0 || 
                           AntColony.Environment.gridContainer.getGridSquare(next).isRevealed() == false);

            
                
            if(mode){      

                    AntColony.Environment.gridContainer.getGridSquare(getPosition()).decrementSoldierCnt();
                    //Update colonyNodeViews to reflect current position of this scout ant

                    AntColony.Environment.gridContainer.getGridSquare(getPosition()).getColNodeView().setSoldierCount(
                    AntColony.Environment.gridContainer.getGridSquare(getPosition()).getNumSoldier());         



                    //if this ant was the only ant in the Square object being left, then hide its icon
                    if(AntColony.Environment.gridContainer.getGridSquare(getPosition()).getNumSoldier() == 0)
                        AntColony.Environment.gridContainer.getGridSquare(getPosition()).getColNodeView().hideSoldierIcon();

                    setPosition(next);

                    //Updates Square object in grid to reflect new position of this scout
                    AntColony.Environment.gridContainer.getGridSquare(next).incrementSoldierCnt();

                    AntColony.Environment.gridContainer.getGridSquare(getPosition()).getColNodeView().setSoldierCount(
                    AntColony.Environment.gridContainer.getGridSquare(getPosition()).getNumSoldier());

                    AntColony.Environment.gridContainer.getGridSquare(getPosition()).getColNodeView().showNode();

                    if(AntColony.Environment.gridContainer.getGridSquare(getPosition()).getNumSoldier() == 1)
                        AntColony.Environment.gridContainer.getGridSquare(getPosition()).getColNodeView().showSoldierIcon();

                    //reveal square for colony

            } else {//attack mode
                act();
                mode = true;
            }

                } else {
                     if(AntColony.Environment.gridContainer.getGridSquare(getPosition()).getNumSoldier() == 1)
                            AntColony.Environment.gridContainer.getGridSquare(getPosition()).getColNodeView().hideSoldierIcon();
                        else{
                        AntColony.Environment.gridContainer.getGridSquare(getPosition()).decrementSoldierCnt();
                        AntColony.Environment.gridContainer.getGridSquare(getPosition()).getColNodeView().setSoldierCount(
                        AntColony.Environment.gridContainer.getGridSquare(getPosition()).getNumSoldier());
                        }
                     
                     

                }  
        
    }

    @Override
    public void remove() {
        
    }

    @Override
    public boolean isSquareOpen() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void act() { //attack!
        
    }

    @Override
    public int getPosition() {
        return this.position; 
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
        this.position = pos;
    }
    
    public int detectBala(int[] pMoves){
        
        boolean detected = false;
        int i, location = 0;
        
        
            i = 0;      
                
        while(i < pMoves.length){            
            
          if(pMoves[location] > 728 || pMoves[location] < 0 || pMoves[location] % 27 == 0 || 
                (getPosition() % 27 == 0) && (pMoves[location] - 1) % 26 == 0 ){
            if(AntColony.Environment.gridContainer.getGridSquare(pMoves[i]).getNumBala() != 0){
                detected = true;
                location = i;
            }
          }
              
            i++;
        } 
        
        
        
        return detected ? pMoves[location]:0;
    }
}
