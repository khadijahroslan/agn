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
import java.util.Comparator;

public abstract class Contribution {
    
    private Donor donor;
    private int contributionNo;
    private static int nextNo = 1000;
    private LocalDate date;
    //private Material material;
    //private Monetary monetary;
    //private Sponsorship sponsorship;

    public Contribution(Donor donor,LocalDate date) {
        this.donor = donor;
        this.contributionNo = ++nextNo;
        this.date = date;
    }
    
    public Donor getDonor() {
        return donor;
    }

    public void setDonor(Donor donor) {
        this.donor = donor;
    }
    
    public int getContributionNo() {
        return contributionNo;
    }

    public void setContributionNo(int contributionNo) {
        this.contributionNo = contributionNo;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    //public abstract String getDonorInfo();
        
    public abstract double getValue();

    public abstract String toString();
    
    public abstract String furtherDetails();

    public abstract String getContributionType();

    public int compareTo(Contribution lc, Contribution rc) {

        return (int) lc.getValue() - (int) rc.getValue();
    }

}
