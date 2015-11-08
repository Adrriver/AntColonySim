/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antcolonysimulation;



/**
 *
 * @author Adrian Rivera
 */
public class Forager extends Ant{
    
    //id
    private int ID;
    //equal to decimal 10, decremented daily
    private double lifeSpan;
    //log of ant moves
    private ArrayStack moveLog;
    //stores units of food ant possesses (wrapped integer 1)
    private int food;
    private boolean mode;//forage mode == true; return to nest mode == false;
    private int position;
    private boolean expired;
    
    public Forager(int ID){
        setID(ID);
        lifeSpan = 1;
        mode = true;
        moveLog = new ArrayStack();
        position = 364;
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
        if(mode == true){//if forager is in foraging mode
            
            //called upon first move, which is out of the Queen's square
            int nextMove = sensePheromone();                     
            //push move onto ArrayStack moveLog
            moveLog.push(nextMove);//push nextMove onto the moves log to set trail pheromone
            //Updates Square object that forager ant is leaving during this move
            
            if(getPosition() != 364){
            AntColony.Environment.gridContainer.getGridSquare(getPosition()).decrementForagerCnt();   
            //Update colonyNodeViews to reflect current position of this scout ant
            
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
            
            setPosition(nextMove);
            
            //Detect existing food in current square, collect 1-unit, and decrement square's food content by 1-unit
            if(AntColony.Environment.gridContainer.getGridSquare(getPosition()).getFood() > 0){
                setFood(1);
                AntColony.Environment.gridContainer.getGridSquare(getPosition()).decrementFood();
                mode = false;
                depositPheromone();
            }
        } else {
            //To do: program return-to-nest-mode move method
            AntColony.Environment.gridContainer.getGridSquare(getPosition()).getColNodeView().setPheromoneLevel(position);
            
            
        }
            
        
        ageAnt();
    }
    
    public void depositPheromone(){
        AntColony.Environment.gridContainer.getGridSquare(getPosition()).setPheromone("grow");
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
    public void setPosition(int pos){
        this.position = pos;
    }
    @Override
    public int getPosition() {
        return this.position;
    }

    
    public int getFood() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public void setFood(int unit) {
        this.food = unit;
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
       this.ID = ID;
    }
    @Override
    public int getID(){
        return ID;
    }
    //Returns the position of the square within SquareContainer that contains
    //the highes concentration/amount of pheromone
    public int sensePheromone(){
        int nextMove = getPosition();
        int[] possibleMoves = {getPosition() + 26, getPosition() + 27, getPosition() + 28, 
                                getPosition() - 26, getPosition() - 27, getPosition() - 28,
                                    getPosition() + 1, getPosition() - 1};
        for(int move:possibleMoves){
            int maxPher = 0, temp;
            if(move != 364){
                temp = AntColony.Environment.gridContainer.getGridSquare(move).getPheromone();
            
                if(temp >= maxPher)
                    maxPher = temp;
                    if(AntColony.Environment.gridContainer.getGridSquare(move).isRevealed() == true)
                        nextMove = move;
            }
        }
        
        return nextMove;
    }
}
