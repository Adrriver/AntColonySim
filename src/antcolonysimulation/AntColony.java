/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antcolonysimulation;

import java.awt.Component;

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
            
            
            if(x == 14 && y == 14){                
                colonyView.addColonyNodeView((ColonyNodeView) cnvArray.get(i), x, y);
                v = (ColonyNodeView)cnvArray.get(i);
                v.setID("[" + String.valueOf(x) + "," + String.valueOf(y) + "]");
                v.setQueen(true);                
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
        } else if ( eType == SimulationEvent.SCOUT_TEST_EVENT){
            
        } else if ( eType == SimulationEvent.FORAGER_TEST_EVENT){
            
        } else if ( eType == SimulationEvent.SOLDIER_TEST_EVENT){
            
        } else if ( eType == SimulationEvent.RUN_EVENT){
            
        } else { //eType == SimulationEvent.STEP_EVENT
            
        }
            
            
            
    }
        
        private static class Environment{
            private static SquareContainer gridContainer;
                
                public static void init(ColonyView cv, ArrayList cnv){
                    System.out.println(cnv.size());
                    gridContainer = new SquareContainer(cv, cnv);
                    gridContainer.getGridSquare(392).getColNodeView().showQueenIcon();
                    
                    
                    
                }
            
        }
    /* Ant classes defined below */  
    private class Queen extends Ant{
        
        private Queen(){
            
        }
                
        private void hatchMember(){

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
        public void setID() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
            
    }
    
    private class Scout extends Ant{
        
        public Scout(){
            
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
        public void setID() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
    private class Forager extends Ant{
             //stores units of food ant possesses (wrapped integer 1)
        private int food;

        public Forager(){
            
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
        public void setID() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
    private class Soldier extends Ant{
        
        public Soldier(){
            
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
        public void setID() {
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
    public void setID() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    }
    
    
}
