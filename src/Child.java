/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * The Child class contains the childs attributes and methods
 * @author Khadijah Roslan
 */
import java.time.*;
import java.util.ArrayList;
import java.util.Comparator;

public class Child{
    private String childName;
    private String MyKadNo;
    private String homeAddress;
    private LocalDate dateOfBirth;
    private ArrayList<Sponsorship> childSponsorships;

    public Child(String childName, String MyKadNo, String homeAddress, LocalDate dateOfBirth) {
        this.childName = childName;
        this.MyKadNo = MyKadNo;
        this.homeAddress = homeAddress;
        this.dateOfBirth = dateOfBirth;
        this.childSponsorships = new ArrayList<>();
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public String getMyKadNo() {
        return MyKadNo;
    }

    public void setMyKadNo(String MyKadNo) {
        this.MyKadNo = MyKadNo;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public ArrayList<Sponsorship> getChildSponsorships() {
        return childSponsorships;
    }

    public void setChildSponsorships(ArrayList<Sponsorship> childSponsorships) {
        this.childSponsorships = childSponsorships;
    }
    
    public ArrayList<Sponsorship> getSponsorships() {
        return childSponsorships;
    }
    
    public int totalSponsorshipPercentage(){
        int total= 0;
        for(Sponsorship s:this.getChildSponsorships())
            total = total + s.getPercentage();
        return total;
    }
    
    public Sponsorship addSponsorship(Sponsorship s){
        childSponsorships.add(s);
        return s;
    }
    
    public Child findLessSponsorship(){
        int totalPercentage = 0;
        for(Sponsorship s:childSponsorships){
            totalPercentage = totalPercentage + s.getPercentage();
            if (totalPercentage<100)
                return this;
        }
        return null;
    }
    
    public int getChildAge(){
        return LocalDate.now().getYear()-this.getDateOfBirth().getYear();
    }
    
    public String toString(){
        return "Child Name: " + this.childName 
                + ", MyKad No: " + this.MyKadNo
                + ", DOB: " + this.dateOfBirth
                + ", Address: " + this.homeAddress;
    }

}
