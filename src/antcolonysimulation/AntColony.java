/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antcolonysimulation;

import java.awt.Component;
import java.util.Random;

/**
 *
 * @author Adrian Rivera
 */
public class AntColony implements SimulationEventListener {

    private AntSimGUI antSimGUI;
    private ColonyView colonyView;
    private static ArrayList cnvArray; //Stores ColonyNodeView instances which comprise grid
    
    
    public AntColony(){
        
        antSimGUI = new AntSimGUI();  
        colonyView = new ColonyView(27, 27);
        antSimGUI.initGUI(colonyView);
        ColonyNodeView v;
        
        cnvArray = new ArrayList();
        for(int i = 0, x = 0, y = 0; i < 729; i++){
            cnvArray.add(new ColonyNodeView());
            
            
            if(x == 13 && y == 13){                
                colonyView.addColonyNodeView((ColonyNodeView) cnvArray.get(i), x, y);
                v = (ColonyNodeView)cnvArray.get(i);
                v.setID("[" + String.valueOf(x) + "," + String.valueOf(y) + "]");
                v.setQueen(true);                
                v.showNode();
                v.showForagerIcon();
                v.showScoutIcon();
                v.showSoldierIcon();                
                v.setSoldierCount(10);
                v.setScoutCount(4);
                v.setForagerCount(50);
                v.setFoodAmount(1000);
                
            
            } else if( (x == 12 && y == 13) || (x == 14 && y == 13) || (x==12 && y==12) || (x==13 && y==12) || (x==14 && y==12)
                        || (x==12 && y==14) || (x==13 && y==14) || (x==14 && y==14)){
                colonyView.addColonyNodeView((ColonyNodeView) cnvArray.get(i), x, y);
                v = (ColonyNodeView) cnvArray.get(i);
                v.showNode();
            } else {                                                                    //add all ColonyNodeView objects to then
                colonyView.addColonyNodeView((ColonyNodeView)cnvArray.get(i), x, y); //be read by (non-static) Square.setColonyNodeView
            }                                                            //method, thereby registering a cnv to push to
            
            if((y+1) % 27 == 0){               
                x++;
                y = 0;
            } else 
                y++;   
        }                                                
        
        Environment.init(colonyView, cnvArray);
        
        
        antSimGUI.addSimulationEventListener(this);
    }
        
    public static ArrayList getCnvArray(){
        return cnvArray;
    }

    @Override
    public void simulationEventOccurred(SimulationEvent simEvent) {
        int eType = simEvent.getEventType();
        /* Initialize per normal setup specs */
        if(eType == SimulationEvent.NORMAL_SETUP_EVENT){
            
        } else if ( eType == SimulationEvent.QUEEN_TEST_EVENT){
            AntColony.Queen queen = new AntColony.Queen();
            queen.hatchMember();
        } else if ( eType == SimulationEvent.SCOUT_TEST_EVENT){
            
        } else if ( eType == SimulationEvent.FORAGER_TEST_EVENT){
            
        } else if ( eType == SimulationEvent.SOLDIER_TEST_EVENT){
            
        } else if ( eType == SimulationEvent.RUN_EVENT){
            
        } else { //eType == SimulationEvent.STEP_EVENT
            
        }
            
            
            
    }
        
        private static class Environment{
            private static SquareContainer gridContainer;
            private static LinkedList antColonyList;
                public static void init(ColonyView cv, ArrayList cnv){
                    antColonyList = new LinkedList();                   
                    gridContainer = new SquareContainer(cv, cnv);
                    gridContainer.getGridSquare(364).getColNodeView().showQueenIcon();
                    
                    
                    
                }
            
        }
    /* Ant classes defined below */  
    private class Queen extends Ant{
        private int life;     
        private Random rand;
        private int ID;
        private int hatchID;
        
        private Queen(){
            ID = 0;
            life = 20;
            rand = new Random();
        }
                
        private void hatchMember(){
            int type = rand.nextInt(4) + 1;
            hatchID++;
            if(type == 3 || type == 4){
                Environment.antColonyList.add(new AntColony.Forager(hatchID));
            } else if( type == 1) {
                Environment.antColonyList.add(new AntColony.Soldier(hatchID));
            } else { // type == 2
                Environment.antColonyList.add(new AntColony.Scout(hatchID));
            }
                
            
        }

        private void consumeFood(){

        }

        @Override
        public boolean expire() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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


        @Override
        public void ageAnt() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public int getID() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
            
    }
    
    private class Scout extends Ant{
        private int ID;
        public Scout(int id){
            this.ID = id;
        }
        @Override
        public boolean expire() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

        @Override
        public void ageAnt() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public int getID() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
    private class Forager extends Ant{
             //stores units of food ant possesses (wrapped integer 1)
        private int food;
        private int ID;

        public Forager(int id){
            this.ID = id;
        }
        @Override
        public boolean expire() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public int getID() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
    private class Soldier extends Ant{
        private int ID;
        
        public Soldier(int id){
            this.ID = id;
        }
        @Override
        public boolean expire() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

        @Override
        public void ageAnt() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public int getID() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
    private class Bala extends Ant{
        public Bala(){
            
        }
        @Override
    public boolean expire() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    @Override
    public void ageAnt() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getID() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    }
    
    
}
