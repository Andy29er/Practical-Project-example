// Main.java

package practicalprojectexample;

import practicalprojectexample.controller.menu.MenuItem;
import practicalprojectexample.utils.SessionManager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        SessionManager.getSessionFactory(); // to force an initialization

        for (int i = 1; i < 100; i++) {
            System.out.println("////////////////////////////////////////");

        }

        MenuItem selectedOption = MenuItem.UNKNOWN;

        while (selectedOption != MenuItem.EXIT) {
            System.out.println();
            MenuItem.printMenuItems(); // prints menu
            System.out.println("Please select an option: ");
            try {
                int numericOption = Integer.parseInt(scanner.nextLine());
                selectedOption = MenuItem.searchByOption(numericOption);
            } catch (NumberFormatException e) {
                System.out.println("Please use a numeric value");
                selectedOption = MenuItem.UNKNOWN; // otherwise prints extra when introducing non numeric values
            }

            switch (selectedOption) {
                case ADD_VET:
                    System.out.println("Add vet not implemented");
                    break;
                case UPDATE_VET:
                    System.out.println("Update vet not implemented");
                    break;
                case DELETE_VET:
                    System.out.println("Delete vet not implemented");
                    break;
                case VIEW_VET_LIST:
                    System.out.println("View vet list not implemented");
                    break;
                case EXIT:
                    System.out.println("Good bye!");
                    break;
                case UNKNOWN:
                    System.out.println("Please insert a valid option");
                    break;
                default:
                    System.out.println("Option not implemented");
                    break;
            }
        }

        System.out.println();

        SessionManager.shutdown();
    }
}