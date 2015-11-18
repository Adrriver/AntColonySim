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
public class AntFrequency {
    
    private String type;
    private int freq;
    private boolean killed;
    private int position;
    
    public AntFrequency(String type, int freq){
        this.type = type;
        this.freq = freq;
    }
    
    public AntFrequency(String type, int freq, boolean killed){
        this.type = type;
        this.freq = freq;
        this.killed = killed;
    }
    
    public String getType(){
        return this.type;
    }
    
    public int getFreq(){
        return this.freq;
    }
    
    public void setKilled(boolean val){
        this.killed = val;
    }
    public boolean getKilled(){
        return this.killed;
    }
    
    public void setPos(int pos){
        this.position = pos;
    }
    
    public int getPos(int pos){
        return this.position;
    }
}
