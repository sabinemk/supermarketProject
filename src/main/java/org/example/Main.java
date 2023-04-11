package org.example;

import java.util.Scanner;


/*
FIX:
- do the log ins

 */
public class Main {
    public static void main(String[] args) {

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Welcome to MarketSuper!\n" +
                    "please enter a number corresponding to your user type: \n" +
                    "1 - customer \n" +
                    "2 - sales representative \n" +
                    "3 - management");
            String userType = scanner.nextLine();


            if (userType.equals("1")) {

                System.out.println("Welcome, customer! \n" +
                        "please enter the number for the action to be performed: \n" +
                        "1 - buy an item \n" +
                        "2 - search for a specific item \n" +
                        "3 - view list of items\n" +
                        "4 - view your balance\n" +
                        "5 - exit ");
                String customerChoice = scanner.next();

                switch (customerChoice){
                    case "1":
                        //phonebookController.buyItem();
                        break;
                    case "2":
                        //phonebookController.searchItem();
                        break;
                    case "3":
                        //phonebookController.viewItems();
                        break;
                    case "4":
                        //phonebookController.viewBalance();
                        break;
                    case "5":
                        System.out.println("Good bye!");
                        System.exit(0);
                    default:
                        System.out.println("please enter a valid menu option");
                }

                break;
            } else if (userType.equals("2")) {
                System.out.println("Welcome, sales rep!! \n" +
                        "please enter the number for the action to be performed: \n" +
                        "1 - register a new item \n" +
                        "2 - search for a specific item \n" +
                        "3 - register a new customer \n" +
                        "4 - view item availability\n" +
                        "5 - exit ");
                String repChoice = scanner.next();

                switch (repChoice){
                    case "1":
                        //phonebookController.registerItem();
                        break;
                    case "2":
                        //phonebookController.searchItem();
                        break;
                    case "3":
                        //phonebookController.registerCustomer();
                        break;
                    case "4":
                        //phonebookController.viewItems();
                        break;
                    case "5":
                        System.out.println("Good bye!");
                        System.exit(0);
                    default:
                        System.out.println("please enter a valid menu option");
                }
                break;

            } else if (userType.equals("3")) {
                System.out.println("Welcome, manager! \n" +
                        "please enter the number for the action to be performed: \n" +
                        "1 - view item availability \n" +
                        "2 - search for a specific item \n" +
                        "3 - create a profit and loss statement\n" +
                        "4 - exit ");
                String managerChoice = scanner.next();

                switch (managerChoice){
                    case "1":
                        //phonebookController.viewItems();
                        break;
                    case "2":
                        //phonebookController.searchItem();
                        break;
                    case "3":
                        //phonebookController.createStatement();
                        break;
                    case "4":
                        System.out.println("Good bye!");
                        System.exit(0);
                    default:
                        System.out.println("please enter a valid menu option");
                }
                break;

            } else {
                System.out.println("please enter a valid user type!");
                System.out.println("press any key + 'enter' to try again or 'e' to exit the program");
                String exitOrStay = scanner.next();

                if (exitOrStay.equals("e")) {
                    System.exit(0);
                }
            }
        }







    }
}




/*
Build an application for a supermarket. The application should be able to collect information about products in the
supermarket (name, the total number of the product available, price, weight and etc).
Should have a main menu which is controlled by a loop where users can choose what they want to do
e.g add new user, add product, buy product, etc.We should be able to buy multiple products from the supermarket
and every time a product is bought, the number of the available products should go down. Also should be a report on
how much has been sold and the history of sales.For products, we would like to track the cost of the goods supplied to
the shop and how much is the selling price. This way, we can also provide a report of sales to the shop owner
(like a profit and loss statement)When the product is finished, the system should show an error if you try to
buy the product error should be “item is sold out”.It should be possible to tell what you want to buy
from the supermarket by inputing the name of the product and how many you want to buy at a time.
If you can, try using JDialog or JavaFX for user interface and collecting information. If it is not possible,
then you can user just scanner or  JOptionPane.For user, it should be possible to provide name, email,
password and balance for user in the supermarket (multiple user can be registered) and when a user will buy product,
user balance should be reduced. If the user doesn’t have money left also error should be shown
to the user “not enough money to buy product”Should be possible to register new user or switch users
Only certain type of users can add product to the supermarket, made different types for users such as
(buyer, sales representative, owner etc), at start of program, ask user to login using their info
then direct them to the correct menu for their user type.Should be possible collect all information
about the product user etc at the start of program and then ask the user to enter the name of product
they want to buy, after that quantity and then do all logic what is mentioned in the task.
Save all information to the database or file depending on what you have better understanding of.


 */