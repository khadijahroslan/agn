
import java.util.Comparator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author KhadijahRoslan
 */
public class ContributionByValue implements Comparator<Contribution>{
    public int compare(Contribution lhs,Contribution rhs){
        return (int)lhs.getValue()-(int)rhs.getValue();
    }
}
