// FOLDER: controller | ConsultController.java

package practicalprojectexample.controller;

import practicalprojectexample.model.Consult;
import practicalprojectexample.repository.exception.EntityUpdateFailedException;
import practicalprojectexample.service.ConsultService;
import practicalprojectexample.service.exception.EntityNotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Optional;
import java.util.Scanner;

public class ConsultController {

    private static final DateTimeFormatter CONSULT_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private final ConsultService consultService;
    private final Scanner scanner;

    public ConsultController(ConsultService consultService, Scanner scanner) {
        this.consultService = consultService;
        this.scanner = scanner;
    }

    public void createConsult() {
        try {
            System.out.println("Please insert Vet's ID:");
            long vetID = Long.parseLong(scanner.nextLine().trim());
            System.out.println("Please insert Pet's ID:");
            long petID = Long.parseLong(scanner.nextLine().trim());
            System.out.println("Please insert description:");
            String description = scanner.nextLine().trim();
            System.out.println("Please insert date of consult [YYYY-MM-DD HH:mm]:");
            Date dateOfConsult = Date.from(LocalDateTime.parse(scanner.nextLine(),
                    CONSULT_DATE_TIME_FORMATTER).toInstant(ZoneOffset.of("+3")));
            consultService.createConsult(vetID, petID, dateOfConsult, description);
            System.out.println("Consult was created");
        } catch (NumberFormatException e) {
            System.err.println("Please insert a number:");
        } catch (DateTimeParseException e) {
            System.err.println("Please insert a valid date [YYYY-MM-DD]:");
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        } catch (EntityUpdateFailedException e) {
            System.err.println(e.getMessage());
            System.out.println("Please retry:");
        } catch (EntityNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("Internal server error");
            // e.printStackTrace();
        }
    }

    public void viewAllConsults() {
        consultService.getAllConsults().stream()
                .forEach(consult ->
                        System.out.println(consult.getId() + " " +
                                consult.getVet().getFirstName() + " " +
                                consult.getVet().getLastName() + " " +
                                consult.getPet().getOwnerName() + " " +
                                consult.getAppointmentDate())
                );
    }

    public void viewConsultByID() {
        try {
            System.out.println("Please insert consult ID:");
            long id = Long.parseLong(scanner.nextLine().trim());
            Optional<Consult> optionalConsult = consultService.getConsultById(id);
            if (optionalConsult.isPresent()) {
                Consult consult = optionalConsult.get();
                // System.out.println(optionalConsult.get() + " " + optionalConsult.);
                System.out.println(consult + " " + consult.getVet() + " " + consult.getPet());
            } else {
                System.out.println("Consult ID not found");
            }
        } catch (NumberFormatException e) {
            System.err.println("Please insert a number:");
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("Internal server error");
            // e.printStackTrace();
        }
    }
}