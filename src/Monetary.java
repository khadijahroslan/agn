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
public class Monetary extends Contribution{
    //private Donor donor;
    private double donationAmount;

    public Monetary(Donor donor, LocalDate inDate, double donationAmount) {
        super(donor, inDate);
        this.donationAmount = donationAmount;
    }

    public double getDonationAmount() {
        return donationAmount;
    }

    public void setDonationAmount(double donationAmount) {
        this.donationAmount = donationAmount;
    }
    
    public double getValue(){
        return donationAmount;
    }
    
    public String toString(){
        return "Contribution Type: MONETARY" 
                + ", Contribution No.: " + super.getContributionNo()
                + ", Contribution Date: " + super.getDate()
                + ", Contribution Amount(RM): " + this.getValue()
                + ", Donor's Email: " + super.getDonor().getEmail();
    }
    
    public String getContributionType(){
        return "Monetary";
    }
    
    public String furtherDetails(){
        return "";
    }
 
}
