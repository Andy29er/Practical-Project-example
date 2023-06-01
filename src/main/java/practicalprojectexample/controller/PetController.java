// FOLDER: service | PetServiceImpl.java

package practicalprojectexample.controller;

import practicalprojectexample.model.Pet;
import practicalprojectexample.model.Vet;
import practicalprojectexample.repository.exception.EntityUpdateFailedException;
import practicalprojectexample.service.PetService;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Optional;
import java.util.Scanner;

public class PetController {

    private final Scanner scanner;

    private final PetService petService;

    public PetController(Scanner scanner, PetService petService) {
        this.scanner = scanner;
        this.petService = petService;
    }

    public void createPet() {
        try {
            System.out.println("Please insert a race:");
            String race = scanner.nextLine().trim();  // .trim() to exclude blank spaces
            System.out.println("Is the pet vaccinated? [true/false]");
            boolean isVaccinated = Boolean.parseBoolean(scanner.nextLine().trim());
            System.out.println("Please insert owner name:");
            String ownerName = scanner.nextLine();
            System.out.println("Please insert date of birth [YYYY-MM-DD]:");
            // WRONG: Date dateOfBirth = Date.from(Instant.from(DateTimeFormatter.ISO_LOCAL_DATE.parse(scanner.nextLine())));
            Date dateOfBirth = Date.from(LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ISO_LOCAL_DATE)
                    .atStartOfDay()
                    .toInstant(ZoneOffset.UTC));
            // check in Java, in Instant.java, then DateTimeFormatter.java

            petService.addPet(race, dateOfBirth, isVaccinated, ownerName);
            System.out.println("Pet was created");

        } catch (IllegalArgumentException e) {  // Exceptions that come from Service
            System.err.println(e.getMessage());
        } catch (EntityUpdateFailedException e) { // Database issues
            System.err.println(e.getMessage());
            System.out.println("Please retry");
        } catch (DateTimeParseException e) { // Exception thrown by parse
            System.err.println("Invalid date format");
        } catch (Exception e) {  // My fallback, in case something wasn't caught by now
            e.printStackTrace();
            System.err.println("Internal server error"); // user can't fix these, it's not its fault
        }
    }

    public void viewAllPets() {
        petService.getAllPets().stream()
                .forEach(pet -> System.out.println(pet.getId() + " " + pet.getRace() + " " + pet.getOwnerName()));
    }

    public void viewPetById() {
        try {
            System.out.println("Please enter the pet's ID:");
            long id = Long.parseLong(scanner.nextLine());

            Optional<Pet> optionalPet = petService.getPetByID(id);

            if (optionalPet.isPresent()) { // IF FOUND, then print it:
                System.out.println(optionalPet.get());
            } else {
                System.out.println("Pet not found by ID: " + id);
            }
        } catch (NumberFormatException e) {
            System.err.println("Please insert a valid numeric ID:"); // For error message when entering non numeric values as ID
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("Internal server error.");
        }
    }
}