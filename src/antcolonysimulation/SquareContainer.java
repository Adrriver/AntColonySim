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
public class SquareContainer {
    
    private LinkedList grid;
    private final ColonyView colonyView;
    
    public SquareContainer(ColonyView cView){
        this.colonyView = cView;
        grid = new LinkedList();
        
        for(int i = 0; i < 729; i++){
            grid.add(new Square());
        }
        
        int j = 0, x = 0, y = 0;
        while(j < 729){
            Square s = (Square) grid.get(j++);
            s.setColonyNodeView( (ColonyNodeView)colonyView.getComponentAt(x, y) );
           
                if(y % 26 == 0){
                    x++; 
                    y = 0;
                } else {
                    y++;
                }
        }
    }
    
    public Square getGridSquare(int i){
        return (Square) grid.get(i);
    }
}    

