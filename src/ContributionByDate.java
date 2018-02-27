
import java.util.Comparator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dijah
 */
public class ContributionByDate implements Comparator<Contribution>{
    public int compare(Contribution lhs,Contribution rhs){
        return lhs.getDate().compareTo(rhs.getDate());
    }
        
}
