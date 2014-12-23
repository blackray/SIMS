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
public class Area {
    private final SimpleStringProperty area = new SimpleStringProperty("");

    public Area() {
        setArea("");
    }
    public Area(String area){
        setArea(area);
    }

    public String getArea() {
        return area.get();
    }
    public void setArea(String area){
        this.area.set(area);
    }
    
    
}
