import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        Scanner input = new Scanner(System.in);

        String options;

        String[] firstName = new String[12];     //initialing an array to hold passengers' first names...
        String[] surName = new String[12];       //initialing an array to hold passengers' surnames...

        for(int x=0; x < 12; x++){      //initialize array indexes...
            firstName[x] = "emptyFirstName";
            surName[x] = "emptySurName";
        }

        System.out.println("\t~~~~~ Welcome to Cruise Ship Boarding ~~~~~");

        label:
        while(true) {
            System.out.println();   //Adding extra line...
            System.out.println(
                    "Please enter your preferred option from the list below:\n" +
                            "\tV : To view all cabins\n" +
                            "\tA : To add a customer to a cabin\n" +
                            "\tE : To display empty cabins\n" +
                            "\tD : To delete a customer from the cabin\n" +
                            "\tF : To find cabin from customer name\n" +
                            "\tS : To store program data into the file\n" +
                            "\tL : To load program data from the file\n" +
                            "\tO : To view passengers ordered alphabetically by name\n" +
                            "\tQ : To quite the program\n"
            );

            System.out.print("Enter the option here: ");
            options = input.next().toUpperCase();
            System.out.println();   //Adding extra line...

            switch (options) {
                case "V":
                    viewCabins(firstName, surName);
                    break;
                case "A":
                    addCustomer(firstName, surName);
                    break;
                case "E":
                    displayEmptyCabins(firstName);
                    break;
                case "D":
                    deleteCustomerFromCabin(firstName, surName);
                    break;
                case "F":
                    findCabinFromCustomerName(firstName);
                    break;
                case "S":
                    storeProgramData(firstName, surName);
                    break;
                case "L":
                    loadProgramData();
                    break;
                case "O":
                    viewPassengersByAlphabetically(firstName);
                    break;
                case "Q":
                    break label;
                default:
                    System.out.println("Please enter the correct option and try Again...");
                    break;
            }
        }
    }

    public static void viewCabins(String[] firstName, String[] surName){

        try {
            int cabinNumber = 1;

            for (int y = 0; y < firstName.length; y++) {
                if (firstName[y].equals("emptyFirstName")) {
                    System.out.println("Cabin number " + cabinNumber + " is empty.");
                } else {
                    System.out.println("Cabin number " + cabinNumber + " is occupied by " + firstName[y] + " " + surName[y] + ".");
                }
                cabinNumber++;
            }
        }catch(Exception e) {
            System.err.println("Error IOException is: " + e);
            e.printStackTrace();
        }
    }

    public static void addCustomer(String[] firstName, String[] surName){

        try {
            Scanner input = new Scanner(System.in);
            int cabinNumber = 0;

            System.out.print("Please enter the cabin number (1-12): ");
            cabinNumber = input.nextInt();

            if ((cabinNumber > 0) && (cabinNumber < 13)) {      //Check the cabin number is valid or not...
                System.out.print("Enter the customer's first name for cabin number " + cabinNumber + ": ");
                String customerFirstName = input.next().toUpperCase();
                System.out.print("Enter the customer's surname for cabin number " + cabinNumber + ": ");
                String customerSurname = input.next().toUpperCase();

                int arrayIndex = cabinNumber - 1;

                firstName[arrayIndex] = customerFirstName;     //Adding customer details into arrays...
                surName[arrayIndex] = customerSurname;
                System.out.println();   //Adding extra line...
                System.out.println("Successfully " + customerFirstName + " " + customerSurname + " added to the cabin number " + cabinNumber + ".");

            } else {
                System.out.println("Please enter a valid cabin number...");
                System.out.println();   //Adding extra line...
            }
        }catch(Exception e) {
            System.err.println("Error IOException is: " + e);
            e.printStackTrace();
        }
    }

    public static void displayEmptyCabins(String[] firstName){

        int cabinNumber = 1;
        int count=0;

        for (int y=0; y<firstName.length; y++) {
            if (firstName[y].equals("emptyFirstName")) {        //Check the cabin is empty or not...
                System.out.println("Cabin number " + cabinNumber + " is empty.");
                count++;        //counting empty cabins...
            }
            cabinNumber++;
        }
        System.out.println();   //Adding extra line...
        System.out.println(count + " cabins are empty.");
    }

    public static void deleteCustomerFromCabin(String[] firstName, String[] surName){

        try {
            Scanner input = new Scanner(System.in);
            int cabinNumber = 0;

            while ((cabinNumber < 1) || (cabinNumber > 12)) {        //checking if the cabin number is valid or not...
                System.out.print("Enter passenger's cabin number that you want to remove from the cabin: ");
                cabinNumber = input.nextInt();
            }

            int arrayIndex = cabinNumber - 1;

            if (firstName[arrayIndex].equals("emptyFirstName")) {
                System.out.println("The cabin you entered is already empty.");
            } else {     //deleting customer from the cabin...
                String passengerFirstName = firstName[arrayIndex];
                String passengerLastName = surName[arrayIndex];
                firstName[arrayIndex] = "emptyFirstName";
                surName[arrayIndex] = "emptySurName";
                System.out.println(passengerFirstName + " " + passengerLastName + " deleted from cabin number " + cabinNumber + ".");
            }
        }catch(Exception e) {
            System.err.println("Error IOException is: " + e);
            e.printStackTrace();
        }
    }

    public static void findCabinFromCustomerName(String[] firstName){

        try {
            Scanner input = new Scanner(System.in);

            System.out.print("Enter the passenger's first name to find the cabin number: ");
            String customerName = input.next().toUpperCase();

            for (int x=0; x<firstName.length; x++) {
                String findName = firstName[x];
                if (findName.equalsIgnoreCase(customerName)) {
                    System.out.println(customerName + "'s cabin number is " + (++x) + ".");
                }
            }
        }
        catch (Exception e) {
            System.err.println("Error IOException is: " + e);
            e.printStackTrace();
        }
    }

    public static void storeProgramData(String[] firstName, String[] surName){

        try {
            FileWriter writeFile = new FileWriter("Cabin_Details.txt");       //to open the file...

            int cabinNumber = 1;         //printing details into the file...
            for (int y=0; y<firstName.length; y++) {
                if (firstName[y].equals("emptyFirstName")) {
                    writeFile.write("Cabin number " + cabinNumber + " is empty.\n");
                }
                else {
                    writeFile.write("Cabin number " + cabinNumber + " occupied by " + firstName[y] + " " + surName[y] + ".\n");
                }
                cabinNumber++;
            }
            writeFile.close();       //to close the file...
            System.out.println("The data was successfully entered into the document.");
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }

    public static void loadProgramData(){

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

    public static void viewPassengersByAlphabetically(String[] passenger){

        String[] passengersByAlphabetically = new String[passenger.length];
        for(int x=0; x<passenger.length; x++){         //To initialize all passengers into separate array...
            passengersByAlphabetically[x] = passenger[x];
        }

        for (int y = 0; y < passenger.length-1; y++) {     //Hold each every element...
            for (int x = 0; x < passenger.length-1; x++) {     //Check for remaining elements...
                if(passengersByAlphabetically[x].compareTo(passengersByAlphabetically[x+1]) > 0){      // To swap the customers if it's required.
                    String output = passengersByAlphabetically[x];
                    passengersByAlphabetically[x]=passengersByAlphabetically[x+1];
                    passengersByAlphabetically[x+1] = output;
                }
            }
        }

        System.out.println("Passengers names by alphabetically order.");
        System.out.println();   //Adding extra line...

        for(int x = 0; x < passenger.length; x++){      //To remove empty cabins from array...
            if(passengersByAlphabetically[x] != "emptyFirstName"){
                System.out.println(passengersByAlphabetically[x]);        //To print the passengers...
            }
        }
    }
}
