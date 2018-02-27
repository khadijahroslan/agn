
import static java.lang.Double.parseDouble;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.*;
import java.util.Scanner;

/**
 * The system is about registering children for sponsorship as well as monetary
 * and material contribution It will record the information details of the
 * children, the sponsorship they received, the donor's contribution along with
 * the contribution details User can use the system to enter details and view
 * the information as well as filtering information using date range and sort
 * information accordingly
 *
 * @author Khadijah Roslan
 */
public class AGNConsole {

    static Scanner sc = new Scanner(System.in);
    static AGN agn;

    public static void main(String[] args) {
        agn = new AGN();
        //int cRecord = 0;
        char choice;
        do {
            System.out.println("");
            System.out.println("1. Record Child");
            System.out.println("2. Record Contribution");
            System.out.println("3. View Contribution Records");
            System.out.println("4. View Sponsorship Records");
            System.out.println("0. Quit");
            System.out.print("Enter choice :");
            choice = sc.next().charAt(0);
            switch (choice) {
                case '1':
                    recordChild();
                    break;
                case '2':
                    recordContribution();
                    break;
                case '3':
                    viewContribution();
                    break;
                case '4':
                    viewSponsorship();
                    break;
                case '0':
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        } while (choice != '0');

    }

    /* USE CASES */
    /**
     * USE CASE #1: A static method to add or update a Child's records The child
     * only will be added/updated if the child's age is 18 and below
     *
     */
    public static void recordChild() {
        sc.nextLine();
        System.out.print("Enter MyKad no.: ");
        String mykad = sc.nextLine();
        Boolean kadVal = checkInput(mykad);
        while (kadVal == false) {
            System.out.println("MyKad no. cannot be empty");
            System.out.print("Enter MyKad no.: ");
            mykad = sc.nextLine();
            kadVal = checkInput(mykad);
        }
        Child c = agn.findChild(mykad);//find whether the child is already exist in the system
        if (c == null) {//if the child is not exist yet
            System.out.print("Enter child's name: ");
            String name = sc.nextLine();
            Boolean nameVal = checkInput(name);
            while (nameVal == false) {
                System.out.println("Name cannot be empty");
                System.out.print("Enter child's name: ");
                name = sc.nextLine();
                nameVal = checkInput(name);
            }
            System.out.print("Enter home address: ");
            String address = sc.nextLine();
            Boolean addVal = checkInput(address);
            while (addVal == false) {
                System.out.println("Address cannot be empty");
                System.out.print("Enter home address: ");
                address = sc.nextLine();
                addVal = checkInput(address);
            }
            System.out.print("Enter DOB [dd/MM/yyyy]: ");
            String dob = sc.next();
            Boolean dobVal = checkInput(dob);
            while (dobVal == false) {
                System.out.println("DOB cannot be empty");
                System.out.print("Enter DOB [dd/MM/yyyy]: ");
                dob = sc.next();
                dobVal = checkInput(dob);
            }
            LocalDate dowb = checkDate(dob);
            while (dowb == null) {
                System.out.println("Invalid date input.");
                System.out.print("Enter DOB [dd/MM/yyyy]: ");
                dob = sc.next();
                dowb = checkDate(dob);
            }
            int yearBorn = dowb.getYear();
            int yearNow = LocalDate.now().getYear();
            int age = yearNow - yearBorn;//calculate the child's age
            sc.nextLine();
            if (age > 18) {//checking the age limit before registering the child
                System.out.println("The child's age is more than 18, cannot be sponsored.");
            } else {
                Child newChild = new Child(name, mykad, address, dowb);
                Child addChild = agn.addChild(newChild);
                if (addChild == null) {
                    System.out.println("Child has not been added");
                } else {//display succesful message of adding the child
                    System.out.print("Child added: ");
                    System.out.println(addChild.toString());
                }
            }
        } else {//if the child is already exist
            System.out.print("The child's record has already exist. Do you want to update records? [Y/N]: ");
            char update = sc.next().charAt(0);
            if (update == 'y' || update == 'Y') {
                sc.nextLine();
                System.out.print("Enter new child's name: ");
                String name = sc.nextLine();
                Boolean nameVal = checkInput(name);
                while (nameVal == false) {
                    System.out.println("Name cannot be empty");
                    System.out.print("Enter child's name: ");
                    name = sc.nextLine();
                    nameVal = checkInput(name);
                }
                System.out.print("Enter new home address: ");
                String address = sc.nextLine();
                Boolean addVal = checkInput(address);
                while (addVal == false) {
                    System.out.println("Address cannot be empty");
                    System.out.print("Enter home address: ");
                    address = sc.nextLine();
                    addVal = checkInput(address);
                }
                System.out.print("Enter new DOB [dd/MM/yyyy]: ");
                String dob = sc.next();
                Boolean dobVal = checkInput(dob);
                while (dobVal == false) {
                    System.out.println("DOB cannot be empty");
                    System.out.print("Enter DOB [dd/MM/yyyy]: ");
                    dob = sc.next();
                    dobVal = checkInput(dob);
                }
                LocalDate dowb = checkDate(dob);
                while (dowb == null) {
                    System.out.println("Invalid date input.");
                    System.out.print("Enter new DOB [dd/MM/yyyy]: ");
                    dob = sc.next();
                    dowb = checkDate(dob);
                }
                int yearBorn = dowb.getYear();
                int yearNow = LocalDate.now().getYear();
                int age = yearNow - yearBorn; //calculate the child's age
                sc.nextLine();

                if (age > 18) { //checking the age limit before updating the child's info
                    System.out.println("The child's age is more than 18, cannot be sponsored.");
                } else {
                    Child addChild = agn.updateChild(c, name, dowb, address);
                    if (addChild == null) {
                        System.out.println("Child has not been added");
                    } else {//display succesful message of updating the child
                        System.out.print("Child updated: ");
                        System.out.println(addChild.toString());
                    }
                }
            }
        }
    }

    /**
     * USE CASE #2: A method to record contribution, user will insert email
     * address of donor, system will check whether donor exist or not if exist
     * all contribution of the donor will be listed if not exist new donor will
     * be created user then will be prompted to select the type of contribution
     * for the donor all contribution records/information will be asked to be
     * entered
     *
     */
    public static void recordContribution() {
        sc.nextLine();
        System.out.print("Enter donor's email: ");
        String email = sc.nextLine();
        Boolean mailVal = checkInput(email);
        while (mailVal == false) {
            System.out.println("Name cannot be empty");
            System.out.print("Enter child's name: ");
            email = sc.nextLine();
            mailVal = checkInput(email);
        }
        Donor d = agn.findDonor(email); //check if donor exist by donor's email
        if (d == null) { //if donor does not exist yet, create new donor
            System.out.print("Enter donor's name: ");
            String name = sc.nextLine();
            Boolean nameVal = checkInput(name);
            while (nameVal == false) {
                System.out.println("Name cannot be empty");
                System.out.print("Enter donor's name: ");
                name = sc.nextLine();
                nameVal = checkInput(name);
            }
            Donor addDonor = new Donor(name, email);
            d = agn.addDonor(addDonor); //add new donor
            System.out.println("Donor added: ");
            System.out.println(d.toString());
        } else { //if donor does exist, print all his contributions
            System.out.println(d.toString());
            System.out.println("Contributions Records: ");
            double totalVal = 0.0;
            for (Contribution cont : d.getContributions()) { //getting all history contribution's value  
                System.out.println(cont.toString());
                totalVal = totalVal + cont.getValue();
            }
            System.out.println("Total Contribution Value (RM): " + totalVal); //print total current contribution
        }
        //to add contribution on the donor
        char contribution;
        do {//asking user to choose which contribution type
            System.out.println("Which contribution do you want to add?");
            System.out.println("1. Monetary");
            System.out.println("2. Material");
            System.out.println("3. Sponsorship");
            System.out.println("0. Stop adding contribution");
            contribution = sc.next().charAt(0);
            switch (contribution) {
                case '1': //if selected Monetary contribution, enter the details
                    System.out.print("Enter contribution date [dd/MM/yyyy]: ");
                    String monDate = sc.next();
                    Boolean dateVal = checkInput(monDate);
                    while (dateVal == false) {
                        System.out.println("Date cannot be empty");
                        System.out.print("Enter contribution date [dd/MM/yyyy]: ");
                        monDate = sc.next();
                        dateVal = checkInput(monDate);
                    }
                    LocalDate dowb = checkDate(monDate);
                    while (dowb == null) {
                        System.out.println("Invalid date input.");
                        System.out.print("Enter contribution date: ");
                        monDate = sc.next();
                        dowb = checkDate(monDate);
                    }
                    System.out.print("Enter amount of contribution(RM): ");
                    String mcAmount = sc.nextLine();
                    Boolean amtVal = checkInput(mcAmount);
                    while (amtVal == false) {
                        System.out.println("Amount cannot be empty");
                        System.out.print("Enter amount of contribution(RM): ");
                        mcAmount = sc.next();
                        amtVal = checkInput(mcAmount);
                    }
                    Double monAmt = Double.parseDouble(mcAmount);
                    Monetary mon = new Monetary(d, dowb, monAmt);
                    Contribution cmon = d.addContribution(mon);
                    System.out.println("Contribution added: ");
                    System.out.println(cmon.toString());
                    break;

                case '2': //if selected Material contribution, enter the details
                    System.out.print("Enter contribution date [dd/MM/yyyy]:: ");
                    String matDate = sc.next();
                    dateVal = checkInput(matDate);
                    while (dateVal == false) {
                        System.out.println("Date cannot be empty");
                        System.out.print("Enter contribution date [dd/MM/yyyy]: ");
                        matDate = sc.next();
                        dateVal = checkInput(matDate);
                    }
                    LocalDate mDate = checkDate(matDate);
                    while (mDate == null) {
                        System.out.println("Invalid date input.");
                        System.out.print("Enter contribution date [dd/MM/yyyy]:: ");
                        matDate = sc.next();
                        mDate = checkDate(matDate);
                    }
                    sc.nextLine();
                    System.out.print("Enter description of contribution: ");
                    String matDesc = sc.nextLine();
                    Boolean desVal = checkInput(matDesc);
                    while (desVal == false) {
                        System.out.println("Description cannot be empty");
                        System.out.print("Enter description of contribution: ");
                        matDesc = sc.next();
                        desVal = checkInput(matDesc);
                    }
                    char matCat;
                    MaterialType mc = null;
                    //Asking user to select category of material contribution
                    do {
                        System.out.println("Choose category of contribution: ");
                        System.out.println("1. Food");
                        System.out.println("2. Clothing");
                        System.out.println("3. School Suplies");
                        System.out.println("0. Stop adding contribution");
                        matCat = sc.next().charAt(0);
                        switch (matCat) {
                            case '1':
                                mc = MaterialType.FOOD;
                                break;
                            case '2':
                                mc = MaterialType.CLOTHING;
                                break;
                            case '3':
                                mc = MaterialType.SCHOOL_SUPPLIES;
                                break;
                            case '0':
                                break;
                            default: //input validation
                                System.out.println("Invalid choice");
                                break;
                        }
                    } while (matCat != '0' && matCat != '1' && matCat != '2' && matCat != '3');
                    System.out.print("Enter value per unit (RM): ");
                    String matAmt = sc.nextLine();
                    Boolean matVal = checkInput(matAmt);
                    while (matVal == false) {
                        System.out.println("Value cannot be empty");
                        System.out.print("Enter value per unit (RM): ");
                        matAmt = sc.next();
                        matVal = checkInput(matAmt);
                    }
                    Double matAmount = Double.parseDouble(matAmt);
                    System.out.print("Enter no of unit contributed: ");
                    int matUnit = sc.nextInt();
                    Material mat = new Material(d, mDate, mc, matDesc, matAmount, matUnit);
                    Contribution cmat = d.addContribution(mat);
                    System.out.println("Contribution added: ");
                    System.out.println(cmat.toString());
                    break;

                case '3': //if selected Sponsorship contribution, choose a child to be sponsored 
                    ArrayList<Child> lessSpons = new ArrayList<>();//getting the children with sponsorship less than 100%
                    for (Child c : agn.getChilds()) {
                        if (c.totalSponsorshipPercentage() < 100) {
                            lessSpons.add(c);
                        }
                    }
                    if (lessSpons.isEmpty()) {//validation if the child for sponsorship exist
                        System.out.println("There is no child to be sponsored");
                    } else {//listing all children who are not fully sponsored
                        System.out.println("Children who are not fully sponsored: ");
                        for (Child c : agn.getChilds()) {
                            System.out.println(c.toString());
                        }
                        Child c;
                        sc.nextLine();
                        System.out.print("Enter MyKad no. of the child for sponsorship: ");//asking user to select a child for sponsorship
                        String mykad = sc.nextLine();
                        Boolean kadVal = checkInput(mykad);//validate input
                        while (kadVal == false) {
                            System.out.println("MyKad no. cannot be empty");
                            System.out.print("Enter MyKad no. of the child for sponsorship: ");
                            mykad = sc.nextLine();
                            kadVal = checkInput(mykad);
                        }
                        c = agn.findChild(mykad);
                        while (c == null) {//validate the existence of child
                            System.out.println("The Mykad No. inserted is not in the list");
                            System.out.print("Enter MyKad no. of the child for sponsorship: ");
                            mykad = sc.nextLine();
                            c = agn.findChild(mykad);
                        }//enter the rest of contribution details
                        System.out.print("Enter contribution date [dd/MM/yyyy]: ");
                        String spoDate = sc.next();
                        Boolean spoVal = checkInput(spoDate);
                        while (spoVal == false) {
                            System.out.println("Date cannot be empty");
                            System.out.print("Enter contribution date [dd/MM/yyyy]: ");
                            spoDate = sc.next();
                            spoVal = checkInput(spoDate);
                        }
                        LocalDate scDate = checkDate(spoDate);
                        while (scDate == null) {
                            System.out.println("Invalid date input.");
                            System.out.print("Enter contribution date: ");
                            spoDate = sc.next();
                            scDate = checkDate(spoDate);
                        }
                        System.out.print("Enter percentage of contribution(%): ");
                        String spoPer = sc.nextLine();

                        Boolean spVal = checkInput(spoPer);
                        while (spVal == false) {
                            System.out.println("Percentage cannot be empty");
                            System.out.print("Enter percentage of contribution(RM): ");
                            spoPer = sc.next();
                            spVal = checkInput(spoPer);
                        }
                        int spoPerc = Integer.parseInt(spoPer);
                        Sponsorship spo = new Sponsorship(d, c, scDate, spoPerc);
                        Sponsorship sp = c.addSponsorship(spo);//adding sponsorship to contribution
                        Contribution cspo = d.addContribution(spo);//adding sponsorship to donor
                        if (sp == null || cspo == null) {//validation
                            System.out.println("Contribution addition failed");
                        } else {//showing feedback of successful addition of contribution
                            System.out.println(sp.toString());
                        }
                    }
                    break;
                case '0':
                    break;
                default://input validation
                    System.out.println("Invalid choice");
                    break;
            }

        } while (contribution != '0');
    }

    /**
     * USE CASE #3: This method will allow user to view and sort records of
     * contributions along with the donor's information and as well as the
     * children information they had sponsored
     */
    public static void viewContribution() {
        ArrayList<Donor> donorList = new ArrayList<>(); //the list of all donors
        ArrayList<Contribution> contList = new ArrayList<>(); //the list of all contribution
        ArrayList<Contribution> CList = new ArrayList<>(); //the contribution list of customized date
        ArrayList<Contribution> monList = new ArrayList<>();
        ArrayList<Contribution> matList = new ArrayList<>();
        ArrayList<Contribution> spoList = new ArrayList<>();

        double total = 0.0;
        donorList = agn.getDonors();

        for (Donor d : donorList) {//addind all contribution for all donors in a list
            contList.addAll(d.getContributions());
        }
        if (contList.isEmpty()) {
            System.out.println("There's no record of any contribution exist in the system");
        } else {
            sc.nextLine();
            System.out.print("Enter start date [dd/MM/yyyy]: ");
            String sdate = sc.nextLine();
            Boolean startdate = checkInput(sdate);
            while (startdate == false) {
                System.out.println("Date cannot be empty");
                System.out.print("Enter start date [dd/MM/yyyy]: ");
                sdate = sc.next();
                startdate = checkInput(sdate);
            }
            LocalDate sDate = checkDate(sdate);
            while (sDate == null) {
                System.out.println("Invalid date input.");
                System.out.print("Enter start date [dd/MM/yyyy]: ");
                sdate = sc.next();
                sDate = checkDate(sdate);
            }
            System.out.print("Enter end date [dd/MM/yyyy]: ");
            String edate = sc.nextLine();
            Boolean enddate = checkInput(edate);
            while (enddate == false) {
                System.out.println("Date cannot be empty");
                System.out.print("Enter end date [dd/MM/yyyy]: ");
                edate = sc.next();
                enddate = checkInput(edate);
            }
            LocalDate eDate = checkDate(edate);
            while (eDate == null) {
                System.out.println("Invalid date input.");
                System.out.print("Enter end date [dd/MM/yyyy]: ");
                edate = sc.next();
                eDate = checkDate(edate);
            }

            for (Contribution c : contList) {
                if (c.getDate().compareTo(sDate) >= 0 && c.getDate().compareTo(eDate) <= 0) {
                    CList.add(c); //add all contributions within the date range to an ArrayList
                }
            }

            System.out.print("Sort by date[D],value[V},or contribution type[T]?: ");
            char sort = sc.next().charAt(0);
            while (sort != 'd' && sort != 'D' && sort != 'v' && sort != 'V' && sort != 't' && sort != 'T') {//validation on user's input
                System.out.println("Invalid input");
                System.out.print("Sort by date[D],value[V},or contribution type[T]?: ");
                sort = sc.next().charAt(0);
            }

            if (sort == 'd' || sort == 'D') { //sort contribution by date range
                Collections.sort(CList, new ContributionByDate());
                Iterator<Contribution> itr = CList.iterator();
                while (itr.hasNext()) {
                    System.out.println(itr.next());
                }
            } else if (sort == 'v' || sort == 'V') { //sort contribution by value within the date range
                Collections.sort(CList, new ContributionByValue());
                Iterator<Contribution> itr = CList.iterator();
                while (itr.hasNext()) {
                    System.out.println(itr.next());
                }
            } else if (sort == 't' || sort == 'T') { //sort contribution by type 

                for (Contribution c : CList) {//group all contributions within the category
                    if (c.getContributionType().equalsIgnoreCase("Monetary")) {
                        monList.add(c);
                    }
                    if (c.getContributionType().equalsIgnoreCase("Material")) {
                        matList.add(c);
                    }
                    if (c.getContributionType().equalsIgnoreCase("Sponsorship")) {
                        spoList.add(c);
                    }
                }
                //printing results
                System.out.println("Monetary Contribution:");
                if (monList.isEmpty()) {
                    System.out.println("No Monetary contribution exist in the list");
                } else {
                    for (Contribution c : monList) {
                        System.out.println(c.toString());
                    }
                }
                System.out.println("Material Contribution:");
                if (matList.isEmpty()) {
                    System.out.println("No Material contribution exist in the list");
                } else {
                    for (Contribution c : matList) {
                        System.out.println(c.toString());
                    }
                }
                System.out.println("Sponsorship Contribution:");
                if (spoList.isEmpty()) {
                    System.out.println("No Monetary contribution exist in the list");
                } else {
                    for (Contribution c : spoList) {
                        System.out.println(c.toString());
                    }
                }

            }
            //displaying the total value of all contributions in the list
            for (Contribution c : CList) {
                total = total + c.getValue();//getting the total value of contributions within the date range
            }
            if (total == 0.0) {
                System.out.println("There is no contribution record for the date range");
            } else {
                System.out.println("Total contribution value (RM): " + total);

                //Asking user whether to see further details of contribution
                System.out.println("Do you want to see more contribution details? [Y,N]: ");
                char seeMore = sc.next().charAt(0);
                while (seeMore == 'y' || seeMore == 'Y') {//input validation
                    System.out.println("Please enter Contribution No.: ");
                    int cNo = sc.nextInt();
                    for (Contribution c : contList) {
                        if (c.getContributionNo() == cNo) {
                            System.out.println(c.toString() + ", Donor's name: " + c.getDonor().getDonorName());
                            System.out.println(c.furtherDetails());
                        } else {//input validation
                            System.out.println("There's no such contribution no. in the list");
                        }
                    }
                    System.out.println("Do you want to see more contribution details? [Y,N]: ");
                    seeMore = sc.next().charAt(0);
                }
            }
        }
    }

    /**
     * USE CASE #4: this method will list all children with their sponsorship
     * details
     */
    public static void viewSponsorship() {
        for (Child c : agn.getChilds()) {
            System.out.println(c.toString());
            System.out.println("Total Sponsorship percentage(%): " + c.totalSponsorshipPercentage());
        }
        //listing out all sponsorship details on selected child
        System.out.println("Do you want to see more sponsorship details? [Y,N]: ");
        char seeMore = sc.next().charAt(0);
        ArrayList<Sponsorship> theSponsors = new ArrayList<>();
        while (seeMore == 'y' || seeMore == 'Y') {
            sc.nextLine();
            System.out.println("Please enter child's MyKad No.: ");
            String cNo = sc.nextLine();
            for (Child c : agn.getChilds()) {
                if (c.getMyKadNo().equalsIgnoreCase(cNo)) {
                    theSponsors = c.getSponsorships();
                }
            }
            if (theSponsors.isEmpty()) {
                System.out.println("There's no sponsor for the child");
            }
            for (Sponsorship s : theSponsors) {
                System.out.println(s.getDonorInfo()
                        + "Percentage of Sponsorship(%): " + s.getPercentage()
                        + ", Value of Sponsorship(RM): " + s.getValue());
            }
            System.out.println("Do you want to see more sponsorship details? [Y,N]: ");
            seeMore = sc.next().charAt(0);
        }
    }

    public static LocalDate checkDate(String dateStr) {
        DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("d/M/y");
        LocalDate theDate = null;
        boolean valid = false;
        try {
            theDate = LocalDate.parse(dateStr, dateformat);
            valid = true;
            if (theDate.isAfter(LocalDate.now())) {
                System.out.println("Date cannot be after today");
                valid = false;
                //break;
            }
        } catch (DateTimeParseException dtpe) {
            System.out.println("Invalid date entered, please re-enter date as dd/mm/yyyy");
        }
        if (valid) {
            return theDate;
        }
        return null;

    }

    public static boolean checkInput(String inStr) {
        if (inStr.equals("")) {
            return false;
        }
        return true;
    }

}
