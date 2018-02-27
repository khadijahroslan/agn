
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * The AGN class acts as the container class for donor and child objects.
 * @author Khadijah Roslan
 */

public class AGN {

    Scanner sc = new Scanner(System.in);
    private ArrayList<Donor> agnDonors;
    private ArrayList<Child> agnChilds;
    
    public AGN() {
        agnDonors = new ArrayList<>();
        agnChilds = new ArrayList<>();
    }

    public Donor addDonor(Donor d) {
            agnDonors.add(d);
            return d;
    }

    public Child addChild(Child c) {
        agnChilds.add(c);
        return c;
    }
    
    public Child updateChild(Child c,String name,LocalDate dobw,String address) {
        c.setChildName(name);
        c.setDateOfBirth(dobw);
        c.setHomeAddress(address);
        return c;
    }

    public Donor findDonor(String email) {
        for (Donor d : agnDonors) {
            if (d.getEmail().equals(email)) {
                return d;
            }
        }
        return null;
    }

    public Child findChild(String mykad) {
        for (Child c : agnChilds) {
            if (c.getMyKadNo().equalsIgnoreCase(mykad)) {
                return c;
            }
        }
        return null;
    }

    public ArrayList<Child> partiallySponsored() {
        ArrayList <Child> partiallySponsoredChildren = new ArrayList<>();
        for (Child c : agnChilds) {
            if (c.findLessSponsorship()==null) 
                System.out.println("");
            else
                partiallySponsoredChildren.add(c);
        }
        return partiallySponsoredChildren;
    }

    public ArrayList<Donor> getDonors() {
        return agnDonors;
    }

    public ArrayList<Child> getChilds() {
        return agnChilds;
    }

}
