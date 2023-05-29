// FOLDER: service | VetServiceImpl.java

package practicalprojectexample.service;

import practicalprojectexample.model.Vet;
import practicalprojectexample.repository.VetRepository;
import practicalprojectexample.repository.exception.EntityUpdateFailedException;
import practicalprojectexample.service.exception.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

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

    @Override
    public List<Vet> getAllVets() {
        return vetRepository.findAll(); //we take our vets from vetRepository
    }

    @Override
    public void updateVet(long id, String lastName, String address, String speciality) throws EntityUpdateFailedException, EntityNotFoundException {
        if (id <= 0) {
            throw new IllegalArgumentException("ID is less or equal to zero");
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

        // In Optional, because it can also be null
        Optional<Vet> optionalVet = vetRepository.findById(id);
        if (optionalVet.isPresent()) {
            Vet vet = optionalVet.get();
            vet.setLastName(lastName);
            vet.setAddress(address);
            vet.setSpeciality(speciality);

            vetRepository.update(vet);
        } else {
            throw new EntityNotFoundException("Vet not found by ID" + id);
        }
    }

    @Override
    public Optional<Vet> findVetById(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID is less or equal to zero");
        }
        return vetRepository.findById(id);
    }
}
