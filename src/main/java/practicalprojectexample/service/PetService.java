// FOLDER: service | PetService.java

package practicalprojectexample.service;

import practicalprojectexample.repository.exception.EntityUpdateFailedException;

import java.util.Date;

public interface PetService {
    void addPet(String race,
                Date dateOfBirth,
                boolean isVaccinated,
                String ownerName) throws EntityUpdateFailedException;
}
