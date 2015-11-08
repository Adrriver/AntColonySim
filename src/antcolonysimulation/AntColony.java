/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antcolonysimulation;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javafx.scene.paint.Color;
import javax.swing.Timer;

/**
 *
 * @author Adrian Rivera
 */
public class AntColony implements SimulationEventListener {

    private AntSimGUI antSimGUI;
    private ColonyView colonyView;
    private Environment environment;
    private static ArrayList cnvArray; //Stores ColonyNodeView instances which comprise visual aspect of the environment grid
    private static int day = 1; //Simulation day
    
    public AntColony(){
        
        antSimGUI = new AntSimGUI(); //new and only GUI instance
        colonyView = new ColonyView(27, 27);
        antSimGUI.initGUI(colonyView);
        ColonyNodeView v;
        
        cnvArray = new ArrayList();
        
        /*Initialize GUI components in for loop*/
        for(int i = 0, x = 0, y = 0; i < 729; i++){
            cnvArray.add(new ColonyNodeView());
            
            // Initialize the Queen's cell
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
                
            //initialize the 8 cells adjacent to the Queen's
            } else if( (x == 12 && y == 13) || (x == 14 && y == 13) || (x==12 && y==12) || (x==13 && y==12) || (x==14 && y==12)
                        || (x==12 && y==14) || (x==13 && y==14) || (x==14 && y==14)){
                colonyView.addColonyNodeView((ColonyNodeView) cnvArray.get(i), x, y);
                v = (ColonyNodeView) cnvArray.get(i);               
                v.setID("[" + String.valueOf(x) + "," + String.valueOf(y) + "]");
                v.showNode();
            } else {                                                                    //add all ColonyNodeView objects to then
                colonyView.addColonyNodeView((ColonyNodeView)cnvArray.get(i), x, y);
                v = (ColonyNodeView)cnvArray.get(i);
                v.setID("[" + String.valueOf(x) + "," + String.valueOf(y) + "]");//be read by (non-static) Square.setColonyNodeView
            }                                                            //method, thereby registering a cnv to push to
            
            if((y+1) % 27 == 0){               
                x++;
                y = 0;
            } else 
                y++;   
        }                                                
        
        environment = new Environment(colonyView, cnvArray);
        
        
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
            environment = null;
            environment = new Environment(colonyView, cnvArray);       
        } else if ( eType == SimulationEvent.QUEEN_TEST_EVENT){            
            Queen.hatchMember();
        } else if ( eType == SimulationEvent.SCOUT_TEST_EVENT){
            
        } else if ( eType == SimulationEvent.FORAGER_TEST_EVENT){
            
        } else if ( eType == SimulationEvent.SOLDIER_TEST_EVENT){
            
        } else if ( eType == SimulationEvent.RUN_EVENT){
            environment.startSimulation();
        } else { //eType == SimulationEvent.STEP_EVENT
            environment.stepThroughSim();
        }
            
            
            
    }
        
        public static class Environment implements ActionListener {
            
        protected static SquareContainer gridContainer;
        private static LinkedList colonyMemberList;
        int ID = 1;
        javax.swing.Timer timer;
        
            /* GUI action provided by button "Normal Setup" initializes Environment objects */
            public Environment(ColonyView cv, ArrayList cnv){
                colonyMemberList = new LinkedList();     
                colonyMemberList.clear();
                
                /* Create initial group of ants and insert them into LinkedList collection */
                for( int i = 0; i < 64; i++){
                    
                    if(i < 10)//10 Soldiers
                        colonyMemberList.addLast(new Soldier(ID++));
                    else if(i >= 10 && i < 59)//50 Foragers
                        colonyMemberList.addLast(new Forager(ID++));
                    else //4 Scouts
                        colonyMemberList.addLast(new Scout(ID++));
                
                }
                
                gridContainer = new SquareContainer(cv, cnv);
                gridContainer.getGridSquare(364).getColNodeView().showQueenIcon();
                //Random number aids in decision of whether to initialize grid square with food (25%)
                double chance = .25;
                //random food unit amount between 500 and 1000 units
                Random foodAmount = new Random();
                for(int i = 0; i < 729; i++){
                    double outcome = Math.random();
                        if(outcome < chance && i != 364){//outcome falls within 25% probability, simulating frequency of outcomes                                                       
                            gridContainer.getGridSquare(i).getColNodeView().setFoodAmount(foodAmount.nextInt(501) + 500);                      
                        }
                            
                }
            }
                //Loops continuously through time cycle construct
            public boolean startSimulation(){
                
                timer = new Timer(1000, this);
                timer.start();
                
                
                return true;
            }
            //Peforms one iteration of time cycle, that is, individual 1-turn 
            public void stepThroughSim(){                
                Queen.hatchMember();
                
                for(int i = 0; i < colonyMemberList.size(); i++){
                    Ant currentAnt = (Ant)colonyMemberList.get(i);                    
                        currentAnt.move();                        
                        currentAnt.ageAnt();
                }
                
                for(int i = 0; i < gridContainer.getGrid().size(); i++){
                    //detect presence of any colony ant and an enemy in the same square, and initiate battle
                    if(AntColony.Environment.gridContainer.getGridSquare(i).getNumBala() > 0 
                            && (AntColony.Environment.gridContainer.getGridSquare(i).getNumForager() > 0 
                            || AntColony.Environment.gridContainer.getGridSquare(i).getNumScout() > 0
                            || AntColony.Environment.gridContainer.getGridSquare(i).getNumSoldier() > 0))
                                {
                                    //To-do: fight algorithm
                                }
                    //On 10th day, decay all pheromone levels by half their existing concentration
                    if(day % 10 == 0){    
                        AntColony.Environment.gridContainer.getGridSquare(i).setPheromone(("decay"));
                        /* update GUI Square pheromone color */
                        updateSquarePheromoneCol(i);
                    }
                }
                       
                //increment day
                day += 1;   
            }
            
            @Override
        public void actionPerformed(ActionEvent e) {
                            
            if(Queen.hasExpired() == true)
                timer.stop();
            else
                stepThroughSim();
        }
            
            public static void addMember(Ant newAnt){
                colonyMemberList.addLast(newAnt);
            }    

        
            
        }
        
    /* Queen class defined below */  
    private static class Queen {
        private static int lifeSpan;     
        private static Random rand = new Random();
        private static int ID;
        private static int foodSupply;
        private static int hatchID;
        private static boolean expired;
        
        private Queen(){
            ID = 0;
            lifeSpan = 20;
            rand = new Random();
        }
                
        private static void hatchMember(){
            
            int type = rand.nextInt(4) + 1;
            hatchID++;
            if(type == 3 || type == 4){
                Environment.addMember(new Forager(hatchID));
            } else if( type == 1) {
                Environment.addMember(new Soldier(hatchID));
            } else { // type == 2
                Environment.addMember(new Scout(hatchID));
            }
                
            
        }

        private void consumeFood(int units){
              foodSupply += units;
        }

        
        public static boolean hasExpired() {            
            return expired;
        }
        public void setExpired(boolean status){
            expired = status;
            
        }
        
        public void move() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        
        public void remove() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        
        public boolean isSquareOpen() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

      
        public void act() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        
        public int position() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }


        
        public void ageAnt() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
       
        public void setID(int ID){
            this.ID = ID;
        }
        
        public int getID() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
            
    }
    
    public static void updateSquarePheromoneCol(int square){
        int pheromoneLevel = AntColony.Environment.gridContainer.getGridSquare(square).getPheromone();
        
        if(pheromoneLevel > 0 && pheromoneLevel <= 200)
            AntColony.Environment.gridContainer.getGridSquare(square).getColNodeView().setBackground(java.awt.Color.magenta);
        else if(pheromoneLevel > 200 && pheromoneLevel <= 400)
            AntColony.Environment.gridContainer.getGridSquare(square).getColNodeView().setBackground(java.awt.Color.blue);
        else if(pheromoneLevel > 400 && pheromoneLevel <= 600)
            AntColony.Environment.gridContainer.getGridSquare(square).getColNodeView().setBackground(java.awt.Color.green);
        else if(pheromoneLevel > 600 && pheromoneLevel <= 800)
            AntColony.Environment.gridContainer.getGridSquare(square).getColNodeView().setBackground(java.awt.Color.yellow);
        else if(pheromoneLevel > 800 && pheromoneLevel <= 1000)
            AntColony.Environment.gridContainer.getGridSquare(square).getColNodeView().setBackground(java.awt.Color.orange);
        else if(pheromoneLevel > 1000)
            AntColony.Environment.gridContainer.getGridSquare(square).getColNodeView().setBackground(java.awt.Color.red);
        else {
            AntColony.Environment.gridContainer.getGridSquare(square).getColNodeView().setBackground(
                    AntColony.Environment.gridContainer.getGridSquare(square).getColNodeView().getBackground());
        }
    }
    
    /*private class Scout extends Ant{
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
    }*/
    
    
}
