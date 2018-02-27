
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Khadijah Roslan
 */
public class Donor {
    private String donorName;
    private String email;
    private ArrayList<Contribution> donorContributions;

    public Donor(String donorName, String email) {
        this.donorName = donorName;
        this.email = email;
        this.donorContributions = new ArrayList<>();
    }

    public String getDonorName() {
        return donorName;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Contribution> getDonorContributions() {
        return donorContributions;
    }

    public void setDonorContributions(ArrayList<Contribution> donorContributions) {
        this.donorContributions = donorContributions;
    }
    
    public Contribution addContribution(Contribution c){
        donorContributions.add(c);
        return c;
    }
    
    public ArrayList<Contribution> getContributions() {
        return donorContributions;
    }
    
    public Donor findDonor(int conNum){
        for(Contribution c:donorContributions)
            if(c.getContributionNo()==conNum)
                return this;
        return null;
    }
    
    public String toString(){
        return "Donor's Name: " + this.donorName + ", Donor's Email: " + this.email;
    }
}
