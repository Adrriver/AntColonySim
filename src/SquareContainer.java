package com.adrianmrivera;

import java.util.*;
/**
 *
 * @author AdrianMRivera
 */
public static class SquareContainer {
    
    private static LinkedList<Square> squareList;
    private static ColonyView colonyView;
    
    public SquareContainer(ColonyView colView){
        setColonyView(colView);
    }
    
    public static void setColView(ColonyView colView){
        this.colonyView = colView;
    }
    
    /** adds generated Square object to field "squareList" */
    public static void add(Square squareInst){
        squareList.add(squareInst);
    }
}
