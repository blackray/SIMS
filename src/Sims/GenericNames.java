/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sims;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author amalcs
 */
public class GenericNames {
    private StringProperty Id;
    public void setId(String value){
        firstIdProperty().setValue(value);
    }
    public StringProperty firstIdProperty(){
        if(Id == null){
            Id = new SimpleStringProperty(this,"Id");
        }
        return Id;
    }
    public String getId(){
        return firstIdProperty().get();
    }
    
    private StringProperty Generic_Name;
    public void setGenericName(String Value){
        firstGenericNameProperty().set(Value);
    }
    public StringProperty firstGenericNameProperty(){
        if(Generic_Name == null){
            Generic_Name = new SimpleStringProperty(this, "Generic_Name");
        }
        return Generic_Name;
    }
    public String getGenericName(){
        return firstGenericNameProperty().get();
    }
    
}
