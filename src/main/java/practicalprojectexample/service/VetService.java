// FOLDER: service | VetService.java

package practicalprojectexample.service;

import practicalprojectexample.model.Vet;
import practicalprojectexample.repository.exception.EntityUpdateFailedException;
import practicalprojectexample.service.exception.EntityNotFoundException;

import java.util.List;

public interface VetService {
    void addVet(
            String firstName,
            String lastName,
            String address,
            String speciality
    ) throws EntityUpdateFailedException;

    List<Vet> getAllVets();

    void updateVet(long id, String lastName, String address, String speciality) throws EntityUpdateFailedException, EntityNotFoundException;
}
