import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        Scanner input = new Scanner(System.in);

        String options;

        Passenger[][] customer = new Passenger[13][4];

        for(int x=1; x<customer.length; x++){
            for(int y=1; y<customer[x].length; y++){
                customer[x][y] = new Passenger();
            }
        }

        System.out.println("~~~~~ Welcome to Cruise Ship Boarding ~~~~~");

        label:
        while(true) {
            System.out.println();   //Adding extra line...
            System.out.println(
                    "Please enter your preferred option from the list below:\n" +
                            "\tV : To view all cabins\n" +
                            "\tA : To add a customer to a cabin\n" +
                            "\tE : To display Empty cabins\n" +
                            "\tD : To delete customer from cabin\n" +
                            "\tF : To find cabin from customer name\n" +
                            "\tS : To store program data into file\n" +
                            "\tL : To load program data from file\n" +
                            "\tO : To view passengers ordered alphabetically by name\n" +
                            "\tT : To view expenses per passenger as well as the total expenses of all passengers\n" +
                            "\tQ : To quite the program\n"
            );

            System.out.print("Enter the option here: ");
            options = input.next().toUpperCase();
            System.out.println();   //Adding extra line...

            switch (options) {
                case "V":
                    Cabin.viewCabins(customer);
                    break;
                case "A":
                    Cabin.addCustomer(customer);
                    break;
                case "E":
                    Cabin.displayEmptyCabins(customer);
                    break;
                case "D":
                    Cabin.deleteCustomerFromCabin(customer);
                    break;
                case "F":
                    Cabin.findCabinFromCustomerName(customer);
                    break;
                case "S":
                    Cabin.storeProgramData(customer);
                    break;
                case "L":
                    Cabin.loadProgramData();
                    break;
                case "O":
                    Cabin.viewPassengersByAlphabetically(customer); //------------------
                    break;
                case "T":
                    Cabin.totalExpenses(customer);
                    break;
                case "Q":
                    break label;
                default:
                    System.out.println("Please enter the correct option and try Again...");
                    break;
            }
        }
    }
}
