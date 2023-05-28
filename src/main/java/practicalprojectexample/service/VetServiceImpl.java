// FOLDER: service | VetServiceImpl.java

package practicalprojectexample.service;

import practicalprojectexample.model.Vet;
import practicalprojectexample.repository.VetRepository;
import practicalprojectexample.repository.exception.EntityUpdateFailedException;

public class VetServiceImpl implements VetService {

    private final VetRepository vetRepository;

    public VetServiceImpl(VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }

    @Override
    public void addVet(
            String firstName,
            String lastName,
            String address,
            String speciality
    ) throws EntityUpdateFailedException { // data validation
        if (firstName == null || firstName.isBlank() || firstName.isEmpty()) {
            throw new IllegalArgumentException("First name is null or blank");
        }

        if (lastName == null || lastName.isBlank() || lastName.isEmpty()) {
            throw new IllegalArgumentException("Last name is null or blank");
        }

        if (address == null || address.isBlank() || address.isEmpty()) {
            throw new IllegalArgumentException("Address name is null or blank");
        }

        if (speciality == null || speciality.isBlank() || speciality.isEmpty()) {
            throw new IllegalArgumentException("Specialty name is null or blank");
        }

        // save
        // create an object of type Vet
        Vet vet = new Vet(firstName, lastName, address, speciality);
        // add it in repository
        vetRepository.save(vet);

    }
}
