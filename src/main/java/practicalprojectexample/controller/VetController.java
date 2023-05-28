// FOLDER: controller | VetController.java

package practicalprojectexample.controller;

import practicalprojectexample.repository.exception.EntityUpdateFailedException;
import practicalprojectexample.service.VetService;

import java.util.Scanner;

public class VetController {

    private final VetService vetService;
    private final Scanner scanner;

    public VetController(VetService vetService, Scanner scanner) {
        this.vetService = vetService;
        this.scanner = scanner;
    }

    public void createVet() {

        try {
            System.out.println("Please insert the vet's first name:");
            String firstName = scanner.nextLine();
            System.out.println("Please insert the vet's last name:");
            String lastName = scanner.nextLine();
            System.out.println("Please insert the vet's address:");
            String address = scanner.nextLine();
            System.out.println("Please insert the vet's specialty:");
            String speciality = scanner.nextLine();

            vetService.addVet(firstName, lastName, address, speciality);
            System.out.println("Vet successfully added.");

        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage()); // serr instead of sout
        } catch (EntityUpdateFailedException e) {
            System.err.println(e.getMessage());
            System.out.println("Please retry");
        } catch (Exception e) {
            System.err.println("Internal server error"); // user can't fix these, it's not its fault
        }
    }
}
