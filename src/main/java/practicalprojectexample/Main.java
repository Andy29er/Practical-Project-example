// Main.java

package practicalprojectexample;

import practicalprojectexample.controller.ConsultController;
import practicalprojectexample.controller.PetController;
import practicalprojectexample.controller.VetController;
import practicalprojectexample.controller.menu.MenuItem;
import practicalprojectexample.repository.ConsultRepositoryImpl;
import practicalprojectexample.repository.PetRepositoryImpl;
import practicalprojectexample.repository.VetRepositoryImpl;
import practicalprojectexample.service.ConsultServiceImpl;
import practicalprojectexample.service.PetServiceImpl;
import practicalprojectexample.service.VetServiceImpl;
import practicalprojectexample.utils.SessionManager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        SessionManager.getSessionFactory(); // to force an initialization

        VetController vetController = new VetController(
                new VetServiceImpl(
                        new VetRepositoryImpl()),
                scanner
        );

        PetController petController = new PetController(
                scanner,  // scanner goes first now
                new PetServiceImpl(
                        new PetRepositoryImpl())
        );

        ConsultController consultController = new ConsultController(
                new ConsultServiceImpl(
                        new VetRepositoryImpl(),
                        new PetRepositoryImpl(),
                        new ConsultRepositoryImpl()
                ),
                scanner
        );

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
                    vetController.createVet();
                    break;
                case UPDATE_VET:
                    vetController.updateVet();
                    break;
                case DELETE_VET:
                    vetController.deleteVetByID();
                    break;
                case VIEW_VET_LIST:
                    vetController.displayAllVets();
                    break;
                case VIEW_VET_BY_ID:
                    vetController.findVetById();
                    break;
                case ADD_PET:
                    petController.createPet();
                    break;
                case VIEW_ALL_PETS:
                    petController.viewAllPets();
                    break;
                case VIEW_PET_BY_ID:
                    petController.viewPetById();
                    break;
                case UPDATE_PET:
                    petController.updatePet();
                    break;
                case DELETE_PET:
                    petController.deletePetById();
                    break;
                case CREATE_CONSULT:
                    consultController.createConsult();
                    break;
                case VIEW_ALL_CONSULTS:
                    consultController.viewAllConsults();
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