/*
    All ant instances require access to the single instance of SquareContainer
    to communicate with the GUI (colonyView)
 */
package antcolonysimulation;

/**
 *
 * @author Adrian_and_Alanna
 */
public class SquareContainer {
    
    private ArrayList grid;
    private final ColonyView colonyView;
    /**
     *  Constructor:
     *  @param cView receives ColonyView reference 
     *  @param cnvArr is a reference to the colonyNodeView collection 
     *         which contains the Square objects that comprise the visual grid
     */
    public SquareContainer(ColonyView cView, ArrayList cnvArr){
        this.colonyView = cView;
        grid = new ArrayList();
        
        for(int i = 0; i < 729; i++){
            grid.add(new Square());
        }
        
        int j = 0, x = 0, y = 0;
        while(j < 729){
            Square s = (Square) grid.get(j);
            s.setColonyNodeView( (ColonyNodeView) cnvArr.get(j++) );
           
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
    
    public ArrayList getGrid(){
        return grid;
    }
    
}    

