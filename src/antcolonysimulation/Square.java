package antcolonysimulation;
/**
 *
 * @author AdrianMRivera
 */
import java.util.*;

public class Square {
    
    private int unitsOfFood;
    private int unitsOfPheromone;
    private boolean open;
    private ColonyNodeView colNodeView;
    private int bala;
    private int scouts;
    private int foragers;
    private int soldiers;
    
    
    public Square(){                      
        setRevealed(false);
        setNumBala(0);
        setNumScout(0);
        setNumSoldier(0);
        setNumForager(0);
        
    }        
    
    public void setColonyNodeView(ColonyNodeView cnv){
        colNodeView = cnv;
    }
    
    public ColonyNodeView getColNodeView(){
        return colNodeView;
    }
    public void setPheromone(String change){
        if(change.equals("grow"))
            this.unitsOfPheromone += 10;
        else if(change.equals("decay"))            
            this.unitsOfPheromone = this.unitsOfPheromone / 2 >= 0? this.unitsOfPheromone/2 : 0 ;            
    }
    public int getPheromone(){
        
        return this.unitsOfPheromone;      
    }
    
    public void setRevealed(boolean status){
        this.open = status; // '-1' == not revealed, '-2' == revealed
    }
    
    public boolean isRevealed(){
        
            return this.open;
    }
    
    public void decrementFood(){
        this.unitsOfFood--;
    }       
    
   public int getFood(){
        
        return this.unitsOfFood;
    }
    
    public void setNumBala(int num){
        this.bala = num;
    }
    
    public void setNumScout(int num){
        this.scouts = num;
    }
    
    public void setNumForager(int num){
        this.foragers = num;
    }
    
    public void setNumSoldier(int num){
        this.soldiers = num;
    }
    
    public int getNumBala(){
        return this.bala;
    }
    
    public int getNumScout(){
        return this.scouts;       
    }
    
    public void incrementScoutCnt(){
        this.scouts++;
    }
    
    public void decrementScoutCnt(){
        this.scouts--;
    }
    
    public int getNumForager(){
        return this.foragers;       
    }
    
    public void incrementForagerCnt(){
        this.foragers++;
    }
    
    public void decrementForagerCnt(){
        this.foragers--;        
    }
    
    public int getNumSoldier(){
        return this.soldiers;
    }
    
    public void startCombatActivity(){
        //To Do: Code 1-on-1-fight mechanism
    }
    
} // end class Square definition
