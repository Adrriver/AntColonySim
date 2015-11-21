/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antcolonysimulation;

import java.util.Random;



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
    private ArrayStack moveLog;
    //stores units of food ant possesses (wrapped integer 1)
    private int food;
    private boolean mode;//forage mode == true; return to nest mode == false;
    private int position;
    private boolean expired;
    private int lastMove;
    private Random random;
    
    public Forager(int ID){
        setID(ID);
        lifeSpan = 3650;
        mode = true;
        moveLog = new ArrayStack();
        position = lastMove = 364;
        random = new Random();
        moveLog.push(364);
    }
    
    public boolean getMode(){
        return mode;
    }
    public void setMode(boolean mode){
        this.mode = mode;
    }
    @Override
    public boolean hasExpired() {
        return expired;
    }
    public void setExpired(boolean status){
        this.expired = status;
    }    
    @Override
    public void move() {
                   
           
        if(!hasExpired()){
        if(mode == true){//if forager is in foraging mode...
            
            //called upon first move, which is out of the Queen's square
            int nextMove = sensePheromone();                     
            //push move onto ArrayStack moveLog
            moveLog.push(nextMove);//push nextMove onto the moves log to set trail pheromone
            //Updates Square object that forager ant is leaving during this move
            
            if(true){
                AntColony.Environment.gridContainer.getGridSquare(getPosition()).decrementForagerCnt();   
                //Update colonyNodeViews to reflect current position of this forager ant
            
                AntColony.Environment.gridContainer.getGridSquare(getPosition()).getColNodeView().setForagerCount(
                AntColony.Environment.gridContainer.getGridSquare(getPosition()).getNumForager());         
        
            }            
           
            //if this ant was the only ant in the Square object being left, then hide its icon
            if(AntColony.Environment.gridContainer.getGridSquare(getPosition()).getNumForager() == 0)
                AntColony.Environment.gridContainer.getGridSquare(getPosition()).getColNodeView().hideForagerIcon();
            
            setPosition(nextMove);
            
            //Updates Square object in grid to reflect new position of this scout
            AntColony.Environment.gridContainer.getGridSquare(nextMove).incrementForagerCnt();
            
            AntColony.Environment.gridContainer.getGridSquare(getPosition()).getColNodeView().setForagerCount(
            AntColony.Environment.gridContainer.getGridSquare(getPosition()).getNumForager());
                        
            AntColony.Environment.gridContainer.getGridSquare(getPosition()).getColNodeView().showNode();
            
            if(AntColony.Environment.gridContainer.getGridSquare(getPosition()).getNumForager() == 1)
                AntColony.Environment.gridContainer.getGridSquare(getPosition()).getColNodeView().showForagerIcon();
            
            
            
            //Detect existing food in current square, collect 1-unit, and decrement square's food content by 1-unit
            if(AntColony.Environment.gridContainer.getGridSquare(getPosition()).getFood() > 0){                
                setFood(1);
                AntColony.Environment.gridContainer.getGridSquare(getPosition()).decrementFood();
                
                AntColony.Environment.gridContainer.getGridSquare(getPosition()).getColNodeView().setFoodAmount(
                 AntColony.Environment.gridContainer.getGridSquare(getPosition()).getFood());
                mode = false;
                
            }
            
        } else {
            if(getPosition() != 364){
                moveLog.pop();
                
                if(AntColony.Environment.gridContainer.getGridSquare(getPosition()).getPheromone() < 1000){
                    depositPheromone();
                    
                }
                    
                AntColony.Environment.gridContainer.getGridSquare(getPosition()).decrementForagerCnt();   
                //Update colonyNodeViews to reflect current position of this scout ant
            
                AntColony.Environment.gridContainer.getGridSquare(getPosition()).getColNodeView().setForagerCount(
                AntColony.Environment.gridContainer.getGridSquare(getPosition()).getNumForager());         
        
                        
           
                //if this ant was the only ant in the Square object being left, then hide its icon
                if(AntColony.Environment.gridContainer.getGridSquare(getPosition()).getNumForager() == 0)
                 AntColony.Environment.gridContainer.getGridSquare(getPosition()).getColNodeView().hideForagerIcon();
            
                setPosition((int)moveLog.get());
            
                //Updates Square object in grid to reflect new position of this scout
                AntColony.Environment.gridContainer.getGridSquare(getPosition()).incrementForagerCnt();
            
                AntColony.Environment.gridContainer.getGridSquare(getPosition()).getColNodeView().setForagerCount(
                AntColony.Environment.gridContainer.getGridSquare(getPosition()).getNumForager());
                        
                AntColony.Environment.gridContainer.getGridSquare(getPosition()).getColNodeView().showNode();
            
                if(AntColony.Environment.gridContainer.getGridSquare(getPosition()).getNumForager() == 1)
                    AntColony.Environment.gridContainer.getGridSquare(getPosition()).getColNodeView().showForagerIcon();
            
                
                    
            
            } else {
                
                //AntColony.Environment.gridContainer.getGridSquare(getPosition()).incrementForagerCnt();
                
                AntColony.Environment.gridContainer.getGridSquare(getPosition()).setFood(
                        AntColony.Environment.gridContainer.getGridSquare(getPosition()).getFood() + getFood());
                AntColony.Environment.gridContainer.getGridSquare(getPosition()).getColNodeView().setFoodAmount(
                        AntColony.Environment.gridContainer.getGridSquare(getPosition()).getFood());
                mode = true;
                
            }
        }
        } else {
                 
                if(AntColony.Environment.gridContainer.getGridSquare(getPosition()).getNumForager() == 1)
                    AntColony.Environment.gridContainer.getGridSquare(getPosition()).getColNodeView().hideForagerIcon();
                
                AntColony.Environment.gridContainer.getGridSquare(getPosition()).decrementForagerCnt();
                AntColony.Environment.gridContainer.getGridSquare(getPosition()).getColNodeView().setForagerCount(
                AntColony.Environment.gridContainer.getGridSquare(getPosition()).getNumForager());
                
                AntColony.Environment.gridContainer.getGridSquare(getPosition()).getColNodeView().setFoodAmount(getFood());
                AntColony.Environment.gridContainer.getGridSquare(getPosition()).setFood(
                    AntColony.Environment.gridContainer.getGridSquare(getPosition()).getFood() + 1);
        }
    }
    
    public void depositPheromone(){
        AntColony.Environment.gridContainer.getGridSquare(getPosition()).setPheromone("grow");      
        if(getPosition() != 364){
        AntColony.Environment.gridContainer.getGridSquare(getPosition()).getColNodeView().setPheromoneLevel(
            AntColony.Environment.gridContainer.getGridSquare(getPosition()).getPheromone());
        }
    }
    
    @Override
    public void act() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public void setPosition(int pos){
        this.position = pos;
    }
    @Override
    public int getPosition() {
        return this.position;
    }

    
    public int getFood() {
        return this.food;
    }

    
    public void setFood(int unit) {
        this.food = unit;
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
    //Returns the position of the square within SquareContainer that contains
    //the highes concentration/amount of pheromone
    public int sensePheromone(){
        int maxPher = 0, temp;
        int bestMove = 0;
        int[] possibleMoves = {getPosition() + 28, getPosition() - 27, getPosition() + 26, 
                                getPosition() - 26, getPosition() + 27, getPosition() - 28,
                                    getPosition() + 1, getPosition() - 1};
        while(bestMove == 0 ){  
           
        for(int i=0; i < 8; i++){
            
            
            if(possibleMoves[i] != 364 || possibleMoves[i] > 728 || possibleMoves[i] < 0 || possibleMoves[i] == 364 || possibleMoves[i] % 27 == 0 || (
                getPosition() % 27 == 0) && (possibleMoves[i] - 1) % 26 == 0){
                
                temp = AntColony.Environment.gridContainer.getGridSquare(possibleMoves[i]).getPheromone();
                   
               
               if(temp >= maxPher && temp != 0 && AntColony.Environment.gridContainer.getGridSquare(possibleMoves[i]).getFood() != 0 
                       && AntColony.Environment.gridContainer.getGridSquare(possibleMoves[i]).isRevealed()){
                    maxPher = temp;
                    bestMove = lastMove = possibleMoves[i];                            
                }
            }
        }
               if(maxPher == 0){
                    for(int mv : possibleMoves){
                        int rand = random.nextInt(8);   
                        if(AntColony.Environment.gridContainer.getGridSquare(possibleMoves[rand]).isRevealed())
                            bestMove = possibleMoves[rand];
                                                    
                }
            }
        }
        System.out.println("Forager lifeSpan: " + lifeSpan);
        return bestMove;
    }
}


