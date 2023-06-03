// FOLDER: service | PetService.java

package practicalprojectexample.service;

import practicalprojectexample.model.Pet;
import practicalprojectexample.repository.exception.EntityUpdateFailedException;
import practicalprojectexample.service.exception.EntityNotFoundException;

import javax.swing.text.html.Option;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PetService {
    void addPet(String race,
                Date dateOfBirth,
                boolean isVaccinated,
                String ownerName
    ) throws EntityUpdateFailedException;

    List<Pet> getAllPets();

    Optional<Pet> getPetByID(long id);

    void updatePet(long id, boolean isVaccinated, String ownerName) throws EntityUpdateFailedException, EntityNotFoundException;

    void deletePetById(long id) throws EntityUpdateFailedException, EntityNotFoundException;
}
