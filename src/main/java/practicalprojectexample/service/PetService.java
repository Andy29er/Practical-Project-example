// FOLDER: service | PetService.java

package practicalprojectexample.service;

import practicalprojectexample.model.Pet;
import practicalprojectexample.repository.exception.EntityUpdateFailedException;

import java.util.Date;
import java.util.List;

public interface PetService {
    void addPet(String race,
                Date dateOfBirth,
                boolean isVaccinated,
                String ownerName
    ) throws EntityUpdateFailedException;

    List<Pet> getAllPets();
}
