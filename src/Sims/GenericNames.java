/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sims;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author amalcs
 */
public class GenericNames {
    private SimpleStringProperty Index = new SimpleStringProperty("");
    private SimpleStringProperty Generic_Name = new SimpleStringProperty("");
    
    public GenericNames(){
        this("","");
    }
    public GenericNames(String Index,String Generic_name){
        setIndex(Index);
        setGeneric_Name(Generic_name);
    }
    
    public void setIndex(String index){
        Index.set(index);
    }
    public String getIndex(){
        return Index.get();
    }
    
    public void setGeneric_Name(String genericname){
        Generic_Name.set(genericname);
    }
    public String getGeneric_Name(){
        return Generic_Name.get();
    }
    
}
