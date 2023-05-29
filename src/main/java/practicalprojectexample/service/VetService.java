// FOLDER: service | VetService.java

package practicalprojectexample.service;

import practicalprojectexample.model.Vet;
import practicalprojectexample.repository.exception.EntityUpdateFailedException;

import java.util.List;

public interface VetService {
    void addVet(
            String firstName,
            String lastName,
            String address,
            String speciality
    ) throws EntityUpdateFailedException;

    List<Vet> getAllVets();
}
