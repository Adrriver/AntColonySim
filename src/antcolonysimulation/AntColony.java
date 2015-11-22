/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antcolonysimulation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author Adrian Rivera
 */
public class AntColony implements SimulationEventListener {

    private static AntSimGUI antSimGUI;
    private ColonyView colonyView;
    private Environment environment;
    private static ArrayList cnvArray; //Stores ColonyNodeView instances which comprise visual aspect of the environment grid
    private static int turn, day; //Simulation day
    private static javax.swing.Timer timer;
    
    public AntColony(){
        
        colonyView = new ColonyView(27, 27);
    
        reset();        
    }
        
    public void reset(){
        turn = 1;
        day = 0;
        
        ColonyNodeView v;
        
        
        colonyView = new ColonyView(27, 27);
        antSimGUI = new AntSimGUI(); //new and only GUI instance
        antSimGUI.initGUI(colonyView);
        
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
                colonyView.addColonyNodeView((ColonyNodeView) cnvArray.get(i), y, x);
                v = (ColonyNodeView) cnvArray.get(i);               
                v.setID("[" + String.valueOf(y) + "," + String.valueOf(x) + "]");
                
                v.showNode();
            } else {                                                                    //add all ColonyNodeView objects to then
                colonyView.addColonyNodeView((ColonyNodeView)cnvArray.get(i), y, x);
                v = (ColonyNodeView)cnvArray.get(i);
                v.setID("[" + String.valueOf(y) + "," + String.valueOf(x) + "]");//be read by (non-static) Square.setColonyNodeView
            }                                                            //method, thereby registering a cnv to push to
            
            if((y+1) % 27 == 0){               
                x++;
                y = 0;
            } else 
                y++;   
        }                                                
        
        environment = new Environment(colonyView, cnvArray);
        
        timer = new Timer(10, environment);
        
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
            timer.stop();
            antSimGUI.dispose();
            reset();
        } else if ( eType == SimulationEvent.QUEEN_TEST_EVENT){            
            Queen.hatchMember();
        } else if ( eType == SimulationEvent.SCOUT_TEST_EVENT){
            
        } else if ( eType == SimulationEvent.FORAGER_TEST_EVENT){
            Forager testForager = new Forager(-1);
            Environment.addMember(testForager);
            for(int i = 10000; i >=0; i--){
                testForager.move();
                testForager.ageAnt();
            }
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
        private static LinkedList bala;
        int ID = 1;
        
        
            /* GUI action provided by button "Normal Setup" initializes Environment objects */
            public Environment(ColonyView cv, ArrayList cnv){
                colonyMemberList = new LinkedList();     
                colonyMemberList.clear();
                
                bala = new LinkedList();
                bala.clear();
                
                gridContainer = new SquareContainer(cv, cnv);
                gridContainer.getGridSquare(364).getColNodeView().showQueenIcon();
                
                /* Create initial group of ants and insert them into LinkedList collection */
                for( int i = 0; i < 63; i++){
                    
                    if(i < 10)//10 Soldiers
                        colonyMemberList.addLast(new Soldier(ID++));
                    else if(i >= 10 && i < 59)//50 Foragers
                        colonyMemberList.addLast(new Forager(ID++));
                    else //4 Scouts
                        colonyMemberList.addLast(new Scout(ID++));
                
                }
                //Reveal to ants initially open nodes
                AntColony.Environment.gridContainer.getGridSquare(363).setRevealed(true);
                AntColony.Environment.gridContainer.getGridSquare(365).setRevealed(true);
                AntColony.Environment.gridContainer.getGridSquare(337).setRevealed(true);
                AntColony.Environment.gridContainer.getGridSquare(336).setRevealed(true);
                AntColony.Environment.gridContainer.getGridSquare(338).setRevealed(true);
                AntColony.Environment.gridContainer.getGridSquare(490).setRevealed(true);
                AntColony.Environment.gridContainer.getGridSquare(491).setRevealed(true);
                AntColony.Environment.gridContainer.getGridSquare(392).setRevealed(true);
                //Random number aids in decision of whether to initialize grid square with food (25%)
                double chance = .25;
                //random food unit amount between 500 and 1000 units
                Random foodAmount = new Random();
                for(int i = 0; i < 729; i++){
                    double outcome = Math.random();
                        if(outcome < chance && i != 364){//outcome falls within 25% probability, simulating frequency of outcomes                                                       
                            int foodUnits = foodAmount.nextInt(501) + 500;
                            gridContainer.getGridSquare(i).getColNodeView().setFoodAmount(foodUnits); 
                            AntColony.Environment.gridContainer.getGridSquare(i).setFood(foodUnits);
                        } else if( i == 364)
                            AntColony.Environment.gridContainer.getGridSquare(i).setFood(1000);
                            
                }
            }
                //Loops continuously through time cycle construct
            public boolean startSimulation(){ 
                Queen.initQueen();
                timer.start();   
                
                
                AntColony.Environment.gridContainer.getGridSquare(364).setNumScout(0);
                AntColony.Environment.gridContainer.getGridSquare(364).setNumSoldier(0);
                return true;
            }
            //Peforms one iteration of time cycle, that is, individual 1-turn 
            public void stepThroughSim(){  
                
                Ant ant = null;
                antSimGUI.setTime("Turns: " + turn + " : " + "Day: " + day);
                Queen.consumeFood();
                Queen.ageAnt();
                System.out.println("Lifespan: " + Queen.lifeSpan);
                if(Queen.hasExpired() && Queen.lifeSpan == 0){
                    timer.stop();
                    JOptionPane.showMessageDialog(antSimGUI,"The Queen has expired!  Please begin a new simulation (Normal Setup)");
                } else if( Queen.hasExpired() && Queen.foodSupply == 0){
                    timer.stop();
                    JOptionPane.showMessageDialog(antSimGUI,"The Queen has starved!  Please begin a new simulation (Normal Setup)");
                }
                boolean expComplete = false;
                double balaOutcome = Math.random();
                
                    if(balaOutcome < .03){
                        Queen.hatchBala();
                    }
                /*age queen each day*/
                if(turn % 10 == 0 || turn == 1){                    
                    Queen.hatchMember();                    
                    day++;
                    
                }
                
                for(int i = 0; i < bala.size(); i++){
                    Bala currentBala = (Bala) bala.get(i);
                    currentBala.ageAnt();
                    
                    AntFrequency af = currentBala.moveBala();
                        
                        if(currentBala.hasExpired()){
                                bala.remove(i);
                                AntColony.Environment.gridContainer.getGridSquare(currentBala.getPosition()).getColNodeView().setBackground(java.awt.Color.green);
                                System.out.println("Bala killed!");
                                continue;
                            }
                            switch(currentBala.getPosition()){
                                case 364:
                                    if(Math.random() < .5){
                                        timer.stop();
                                        JOptionPane.showMessageDialog(antSimGUI, "The Queen is dead!  Please begin a new simulation.");
                                    }
                                break;
                                default:
                                    break;                        

                            }

                        if(af.getKilled()){ //moveBala returns AntFrequency object representing ant battled or def. ant object
                            for(int j = 0; j < colonyMemberList.size(); j++){
                                ant = (Ant) colonyMemberList.get(j);

                                if(currentBala.getPosition() == ant.getPosition() && !expComplete){

                                    ant.setExpired(true);
                                    System.out.println("Colony member lost! " + ant.getClass());
                                    expComplete = true;

                                }
                            }
                            expComplete = false;
                        }
                }
                
                for(int k = 0; k < colonyMemberList.size(); k++){
                    Ant currentAnt = (Ant)colonyMemberList.get(k);
                       if(currentAnt instanceof Soldier){
                            Soldier soldier = (Soldier) currentAnt;
                            moveOutcome oc = soldier.moveSoldier();
                            
                            if(oc.getOutcome()){
                                   
                                if(AntColony.Environment.gridContainer.getGridSquare(oc.getPos()).getNumBala() > 0)
                                    AntColony.Environment.gridContainer.getGridSquare(oc.getPos()).decrementBalaCnt();
                                
                                for(int i = 0; i < bala.size(); i++){
                                    Bala currentBala = (Bala) bala.get(i);
                                    if(currentBala.getPosition() == oc.getPos()){
                                        currentBala.setExpired(true);
                                    }
                                }
                            }
                            
                       } else {
                            currentAnt.move();
                       }
                            currentAnt.ageAnt();
                            
                            if(currentAnt.hasExpired()){                                                                 
                                colonyMemberList.remove(k);
                                if(currentAnt instanceof Forager)
                                    AntColony.Environment.gridContainer.getGridSquare(currentAnt.getPosition()).decrementForagerCnt();
                                else if(currentAnt instanceof Scout)
                                    AntColony.Environment.gridContainer.getGridSquare(currentAnt.getPosition()).decrementScoutCnt();
                                else if(currentAnt instanceof Soldier)
                                    AntColony.Environment.gridContainer.getGridSquare(currentAnt.getPosition()).decrementSoldierCnt();
                                
                                AntColony.Environment.gridContainer.getGridSquare(currentAnt.getPosition()).getColNodeView().setBackground(java.awt.Color.red);
                            }
                       
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
                    if(turn % 10 == 0){    
                        AntColony.Environment.gridContainer.getGridSquare(i).setPheromone(("decay"));
                        AntColony.Environment.gridContainer.getGridSquare(i).getColNodeView().setPheromoneLevel(
                            AntColony.Environment.gridContainer.getGridSquare(i).getPheromone());
                        
                    }
                }
                       
                //increment day
                turn++;   
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
        
        private static void initQueen(){
            ID = 0;
            lifeSpan = 73000;
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
        
        public static void hatchBala(){
            hatchID++;
            Environment.bala.addLast(new Bala(hatchID));
        }
        public static void consumeFood(){
              foodSupply--;
              AntColony.Environment.gridContainer.getGridSquare(364).decrementFood();
              AntColony.Environment.gridContainer.getGridSquare(364).getColNodeView().setFoodAmount(
                    AntColony.Environment.gridContainer.getGridSquare(364).getFood());
              if(foodSupply == 0){
                  setExpired(true);
              }
        }

        public static boolean hasExpired() {            
            return expired;
        }
        public static void setExpired(boolean status){
            expired = status;
            remove();
            
        }
             
        
        public static void remove() {
            timer.stop();
            JOptionPane.showMessageDialog(antSimGUI, "The Queen is dead!  Please begin a new simulation.");
        }
               
        public static void ageAnt() {
        if(lifeSpan - 1 != 0){
            lifeSpan--;
            
        }
        else{
            setExpired(true);
            
        }
        }
       
        public void setID(int ID){
            ID = ID;
        }
        
        public int getID() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
            
    }
    
    
    
    
    
    
    
}
