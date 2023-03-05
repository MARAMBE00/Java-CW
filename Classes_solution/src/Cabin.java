import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Cabin {

    private int cabinNumber;

    public Cabin(){
        cabinNumber = 0;
    }

    public int getCabinNumber(){
        return cabinNumber;
    }

    public void setCabinNumber(int cabinNumber){
        this.cabinNumber = cabinNumber;
    }

    Queue waitingList = new Queue();

    public static void viewCabins(Passenger[][] customer){

        for(int x=1; x<customer.length; x++){
            for(int y=1; y<customer[x].length; y++){
                if(customer[x][y].getFirstName().equals("emptyFirstName")){
                    System.out.println("Cabin number " + x + " - passenger location " + y + " is empty.");
                }
                else {
                    System.out.println("Cabin number " + x + " - passenger " + y + " is occupied by " + customer[x][y].getFirstName() + " " +
                            customer[x][y].getSurName() +".");
                }
            }
        }
    }

    public static void addCustomer(Passenger[][] customer) {

        try {
            Scanner input = new Scanner(System.in);
            int cabinNumber = 0;
            int passengerLocation = 0;

            System.out.println("You can add 3 passenger for a cabin.");
            System.out.println();   //Adding extra line...

            System.out.print("Please enter the cabin number (1-12): ");
            cabinNumber = input.nextInt();

            System.out.print("Please enter passenger location (1-3): ");
            passengerLocation = input.nextInt();

            if ((cabinNumber > 0) && (cabinNumber < 13)) {      //Checking cabin number is valid or not...
                System.out.print("Enter the customer's first name for cabin number " + cabinNumber + ": ");   //Getting customers details from the user...
                String customerFirstName = input.next().toUpperCase();
                customer[cabinNumber][passengerLocation].setFirstName(customerFirstName);

                System.out.print("Enter the customer's surname for cabin number " + cabinNumber + ": ");
                String customerSurname = input.next().toUpperCase();
                customer[cabinNumber][passengerLocation].setSurName(customerSurname);
            } else {
                System.out.println("Please enter a valid cabin number...");
                System.out.println();   //Adding extra line...
            }

            System.out.print("Enter your expenses: $");
            if (input.hasNextDouble()) {
                Double expenses = input.nextDouble();
                customer[cabinNumber][passengerLocation].setExpenses(expenses);
            }

            System.out.println("Successfully " + customer[cabinNumber][passengerLocation].getFirstName() + " " + customer[cabinNumber][passengerLocation].getSurName()
                    + " added to the cabin number " + cabinNumber + " passenger " + passengerLocation + ".");
        }catch(Exception e) {
            System.err.println("Error IOException is: " + e);
            e.printStackTrace();
        }
    }

    public static void displayEmptyCabins(Passenger[][] customer){

        for(int x=1; x<customer.length; x++){
            for(int y=1; y<customer[x].length; y++){
                if(customer[x][y].getFirstName().equals("emptyFirstName")){
                    System.out.println("Cabin " + x + " - passenger location " + y + " is empty.");
                }
            }
        }
    }

    public static void deleteCustomerFromCabin(Passenger[][] customer) {

        try {
            Scanner input = new Scanner(System.in);
            System.out.print("Enter passenger's first name that you want to remove from the cabin: ");
            String firstName = input.next();

            for (int x=1; x<customer.length; x++) {
                for (int y=1; y<customer[x].length; y++) {
                    String name = customer[x][y].getFirstName();
                    if (name.equalsIgnoreCase(firstName)) {     //deleting customer from the cabin...
                        customer[x][y].setFirstName("emptyFirstName");
                        customer[x][y].setSurName("emptySurName");
                        customer[x][y].setExpenses(0.0);
                        System.out.println(name + " is removed from cabin number " + x + " passenger location " + y + ".");
                    }
                }
            }
        }catch(Exception e) {
            System.err.println("Error IOException is: " + e);
            e.printStackTrace();
        }
    }

    public static void findCabinFromCustomerName(Passenger[][] customer) {

        try {
            Scanner input = new Scanner(System.in);
            System.out.print("Enter the passenger's first name to find the cabin number: ");
            String firstName = input.next();

            for (int x=1; x<customer.length; x++) {
                for (int y=1; y<customer[x].length; y++) {
                    String findName = customer[x][y].getFirstName();
                    if (findName.equalsIgnoreCase(firstName)) {
                        System.out.println(customer[x][y].getFirstName() + "'s cabin number is " + x + " and passenger location " + y + ".");
                    }
                }
            }
        }catch(Exception e) {
            System.err.println("Error IOException is: " + e);
            e.printStackTrace();
        }
    }

    public static void storeProgramData(Passenger[][] customer) {
        try {
            FileWriter writeFile = new FileWriter("Cabin_Details.txt");       //to open the file...
            for(int x=1; x< customer.length; x++) {
                for (int y = 1; y < customer[x].length; y++) {       //printing details into the file...
                    String firstName = customer[x][y].getFirstName();
                    String surName = customer[x][y].getSurName();
                    double expenses = customer[x][y].getExpenses();
                    writeFile.write("Cabin number " + x + " passenger " + y + " occupied by " + firstName + " " + surName +
                                "." + " Expenses = $" + expenses + "\n");
                }
            }
            writeFile.close();       //to close the file...
            System.out.println("The data was successfully entered into the document.");
        }
        catch (IOException e){
            System.out.println("An error occurred.");
        }
    }

    public static void loadProgramData() {

        try {
            File wroteFile = new File("Cabin_Details.txt");
            Scanner reader = new Scanner(wroteFile);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                System.out.println(data);
            }
            reader.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
    }

    public static void viewPassengersByAlphabetically(Passenger[][] customer) {

        String[] passengersByAlphabetically = new String[36];

        int placeHolder = 0;

        for (int x=1; x<customer.length; x++) {     //To initialize all passengers into separate array...
            for (int y=1; y<customer[x].length; y++) {
                passengersByAlphabetically[placeHolder++] = customer[x][y].getFirstName();
            }
        }

        for (int x=1; x<passengersByAlphabetically.length; x++) {       //Hold each every element...
            for (int y=1; y<passengersByAlphabetically.length-x-1; y++) {       //Check for remaining elements...
                if (passengersByAlphabetically[y].compareTo(passengersByAlphabetically[y+1]) > 0){
                    String output = passengersByAlphabetically[y];
                    passengersByAlphabetically[y] = passengersByAlphabetically[y+1];
                    passengersByAlphabetically[y+1] = output;
                }
            }
        }

        System.out.println("Passengers names by alphabetically order.");
        System.out.println();   //Adding extra line...

        for (int x=1; x<passengersByAlphabetically.length; x++) {       //To remove empty cabins from array...
            if(passengersByAlphabetically[x] != "emptyFirstName") {
                System.out.println(passengersByAlphabetically[x]);      //To print the passengers...
            }
        }
    }

    public static void totalExpenses(Passenger[][] customer){

        double totalExpenses = 0.0;
        for(int x=1; x< customer.length; x++) {
            for (int j = 1; j < customer[x].length; j++) {      // To get expenses
                String firstName = customer[x][j].getFirstName();
                String surName = customer[x][j].getSurName();
                double expenses = customer[x][j].getExpenses();
                if(firstName != "emptyFirstName" && surName != "emptySurName" && expenses != 0.0) {     //To print the expenses...
                    System.out.println("Cabin " + x + " passenger " + j + " expenses is " + "$" + customer[x][j].getExpenses());
                    totalExpenses = totalExpenses + customer[x][j].getExpenses();
                }
            }
        }
        System.out.println();   //Adding extra line...
        System.out.println("Total expenses of the passengers: $" + totalExpenses);      //To print total expenses...
    }
}
