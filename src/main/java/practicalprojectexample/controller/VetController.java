// FOLDER: controller | VetController.java

package practicalprojectexample.controller;

import practicalprojectexample.model.Vet;
import practicalprojectexample.repository.exception.EntityUpdateFailedException;
import practicalprojectexample.service.VetService;
import practicalprojectexample.service.exception.EntityNotFoundException;

import java.util.Optional;
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

    public void displayAllVets() {
        vetService.getAllVets();
        for (Vet vet : vetService.getAllVets()) {
            System.out.println(vet.getId() + " " + vet.getFirstName() + " " + vet.getLastName());
        }
    }

    public void updateVet() {
        try {
            System.out.println("Please enter the vet's ID:");
            long id = Long.parseLong(scanner.nextLine());

            System.out.println("Please insert the vet's new last name:");
            String lastName = scanner.nextLine();
            System.out.println("Please insert the vet's new address:");
            String address = scanner.nextLine();
            System.out.println("Please insert the vet's new specialty:");
            String speciality = scanner.nextLine();

            vetService.updateVet(id, lastName, address, speciality);
            System.out.println("Vet updated successfully.");

        } catch (NumberFormatException e) {
            System.err.println("Please insert a valid numeric ID:"); // For error message when entering non numeric values as ID
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        } catch (EntityUpdateFailedException e) {
            System.err.println(e.getMessage());
            System.out.println("Please retry.");
        } catch (EntityNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("Internal server error.");
        }
    }

    public void findVetById() {
        try {
            System.out.println("Please enter the vet's ID:");
            long id = Long.parseLong(scanner.nextLine());

            Optional<Vet> optionalVet = vetService.findVetById(id);

            if (optionalVet.isPresent()) { // IF FOUND, then print it:
                System.out.println(optionalVet.get());
            } else {
                System.out.println("Vet not found by ID: " + id);
            }
        } catch (NumberFormatException e) {
            System.err.println("Please insert a valid numeric ID:"); // For error message when entering non numeric values as ID
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("Internal server error.");
        }
    }

    public void deleteVetByID() {
        try {
            System.out.println("Please enter the vet's ID:");
            long id = Long.parseLong(scanner.nextLine());

            vetService.deleteVetById(id);
            System.out.println("Vet deleted successfully.");

        } catch (NumberFormatException e) {
            System.err.println("Please insert a valid numeric ID:"); // For error message when entering non numeric values as ID
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        } catch (EntityNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("Internal server error.");
        }
    }
}