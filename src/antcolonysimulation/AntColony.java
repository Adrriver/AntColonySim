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
public class AntColony {

    private AntSimGUI antSimGUI;
    private ColonyView colonyView;
    private static ArrayList cnvArray; //Stores ColonyNodeView instances which comprise grid
    
    
    public AntColony(){
        
        antSimGUI = new AntSimGUI();  
        colonyView = new ColonyView(27, 27);
        antSimGUI.add(colonyView);
        
        cnvArray = new ArrayList();
        for(int i = 0, x = 0, y = 0; i < 729; i++){
            ColonyNodeView cnv = new ColonyNodeView();
            cnvArray.add(cnv);   
                                                                                  //add all ColonyNodeView objects to then
            colonyView.addColonyNodeView((ColonyNodeView)cnvArray.get(i), x, y); //be read by (non-static) Square.setColonyNodeView
                                                                                 //method, thereby registering a cnv to push to
            if((y+1) % 27 == 0){               
                x++;
                y = 0;
            } else 
                y++;
            
        }                                                
        
        Environment.init(colonyView);
        
    }
        
    public static ArrayList getCnvArray(){
        return cnvArray;
    }
        
        private static class Environment{
            private static SquareContainer gridContainer;

                public static void init(ColonyView cv){
                    gridContainer = new SquareContainer(cv);
                    
                    
                }
            
        }
    
}
