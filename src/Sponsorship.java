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
import java.util.Comparator;
public class Sponsorship extends Contribution {
    
    //private Donor donor;
    private Child child;

    private int percentage;
    private static double maxSponsorshipValue = 150;

    public Sponsorship(Donor donor, Child child, LocalDate inDate, int percentage) {
        super(donor, inDate);
        this.child = child;
        this.percentage = percentage;
    }
    public Child getChild() {
        return child;
    }
    public void setChild(Child child) {
        this.child = child;
    }
    public int getPercentage() {
        return percentage;
    }
    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }
    public double getValue(){
        return percentage*maxSponsorshipValue/100;
    }
    public String toString(){
        return "Contribution Type: SPONSORSHIP" 
                + ", Contribution No.: " + super.getContributionNo()
                + ", Contribution Date: " + super.getDate()
                //+ ", Contribution Percentage(%): " + this.percentage
                + ", Contribution Value(RM): " + this.getValue()
                + ", Donor's Email: " + super.getDonor().getEmail();
                //+ ", Child: " + this.child.toString();
    }
    public String furtherDetails(){
        return "Contribution Percentage(%): " + this.percentage
                + ", Child Name: " + this.child.getChildName()
                + ", Age: " + this.child.getChildAge()
                + ", Home Address: " + this.child.getHomeAddress();
        
    }
    public String getDonorInfo(){
        return "Donor's Email: " + super.getDonor().getEmail()
                + ", Donor's Name: " + super.getDonor().getDonorName();
    }
    public String getChildInfo(){
        return "Child's Name: " + this.child.getChildName()
                + ", Child's Age: " + this.child.getChildAge()
                + ", Child's Address " + this.child.getHomeAddress();
    }
    public String getContributionType(){
        return "Sponsorship";
    }
  
}
