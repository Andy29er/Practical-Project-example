// FOLDER: service | VetService.java

package practicalprojectexample.service;

import practicalprojectexample.repository.exception.EntityUpdateFailedException;

public interface VetService {
    void addVet(
            String firstName,
            String lastName,
            String address,
            String speciality
    ) throws EntityUpdateFailedException;
}
