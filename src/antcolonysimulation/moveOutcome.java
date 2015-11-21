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
class moveOutcome {

    private final int position;
    private final boolean balaDefeated;
    
    public moveOutcome(int position, boolean b) {
        
        this.position = position;
        balaDefeated = b;        
    }
    
    public int getPos(){
       
        return this.position;
    }
    
    public boolean getOutcome(){
        
        return this.balaDefeated;
    }
    
}
