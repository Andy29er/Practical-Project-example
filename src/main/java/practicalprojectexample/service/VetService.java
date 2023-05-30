// FOLDER: service | VetService.java

package practicalprojectexample.service;

import practicalprojectexample.model.Vet;
import practicalprojectexample.repository.exception.EntityUpdateFailedException;
import practicalprojectexample.service.exception.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

public interface VetService {
    void addVet(
            String firstName,
            String lastName,
            String address,
            String speciality
    ) throws EntityUpdateFailedException;

    List<Vet> getAllVets();

    void updateVet(long id, String lastName, String address, String speciality) throws EntityUpdateFailedException, EntityNotFoundException;

    Optional<Vet> findVetById(long id);

    void deleteVetById(long id) throws EntityUpdateFailedException, EntityNotFoundException;
}
