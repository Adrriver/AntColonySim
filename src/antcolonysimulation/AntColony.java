/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antcolonysimulation;

/**
 *
 * @author Adrian_and_Alanna
 */
public class AntColony {

   
    
      private AntSimGUI antSimGUI;
    
    public AntColony(){
        antSimGUI = new AntSimGUI();
        
        antSimGUI.add(new ColonyView(10,10));
    }

    
}
