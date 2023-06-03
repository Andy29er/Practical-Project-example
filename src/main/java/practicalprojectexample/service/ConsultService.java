// FOLDER: service | ConsultService.java

package practicalprojectexample.service;

import practicalprojectexample.repository.exception.EntityUpdateFailedException;
import practicalprojectexample.service.exception.EntityNotFoundException;

import java.util.Date;

public interface ConsultService {
    void createConsult(long vetId, long petId, Date dateOfBirth, String description) throws EntityNotFoundException, EntityUpdateFailedException;
}
