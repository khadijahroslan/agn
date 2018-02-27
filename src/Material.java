/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Khadijah Roslan
 */
import java.time.*;
import java.util.ArrayList;
public class Material extends Contribution{
    //private Donor donor;
    private MaterialType category;
    private String itemDescription;
    private double valuePerUnit;
    private int numUnitsContributed;

    public Material(Donor donor, LocalDate inDate, MaterialType category, String itemDescription, double valuePerUnit, int numUnitsContributed) {
        super(donor, inDate);
        this.category = category;
        this.itemDescription = itemDescription;
        this.valuePerUnit = valuePerUnit;
        this.numUnitsContributed = numUnitsContributed;
    }

    public MaterialType getCategory() {
        return category;
    }

    public void setCategory(MaterialType category) {
        this.category = category;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public double getValuePerUnit() {
        return valuePerUnit;
    }

    public void setValuePerUnit(double valuePerUnit) {
        this.valuePerUnit = valuePerUnit;
    }

    public int getNumUnitsContributed() {
        return numUnitsContributed;
    }

    public void setNumUnitsContributed(int numUnitsContributed) {
        this.numUnitsContributed = numUnitsContributed;
    }
    
    public double getValue(){
        return numUnitsContributed*valuePerUnit;
    }
    public String toString(){
        return "Contribution Type: MATERIAL" 
                + ", Contribution No: " + super.getContributionNo()
                + ", Contribution date: " + super.getDate()
                //+ "\n"
                //+ ", Material Type: " + this.category 
                //+ ", Item Description: " + this.itemDescription 
                //+ ", Value Per Units: RM" +  this.valuePerUnit 
                //+ ", No. of Unit: " + this.numUnitsContributed
                + ", Value(RM): " + this.getValue()
                + ", Donor's Email: " + super.getDonor().getEmail();
    }
    
    public String furtherDetails(){
        return "Material Type: " + this.category  
                + ", Item Description: " + this.itemDescription 
                + ", Value Per Units: RM" +  this.valuePerUnit 
                + ", No. of Unit: " + this.numUnitsContributed;
    }
    
    public String getContributionType(){
        return "Material";
    }
    
    //@Override
    public int compareTo(Material t) {
        return (int)this.getValue()-(int)t.getValue();
    }
   
}
