// FOLDER: service | PetServiceImpl.java

package practicalprojectexample.service;

import practicalprojectexample.model.Pet;
import practicalprojectexample.repository.PetRepository;
import practicalprojectexample.repository.exception.EntityUpdateFailedException;
import practicalprojectexample.service.exception.EntityNotFoundException;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;

    public PetServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public void addPet(String race, Date dateOfBirth, boolean isVaccinated, String ownerName) throws EntityUpdateFailedException {
        if (race == null || race.isBlank() || race.isEmpty()) {
            throw new IllegalArgumentException("Please insert a valid race");
        }
        if (dateOfBirth == null) {
            throw new IllegalArgumentException("Please insert a date of birth");
        }
        if (dateOfBirth.after(Date.from(Instant.now().plus(Duration.ofDays(1))))) {
            throw new IllegalArgumentException("Please insert a date from the past");
        }
        if (ownerName == null || ownerName.isEmpty() || ownerName.isBlank()) {
            throw new IllegalArgumentException("Please insert a valid owner name");
        }

        Pet pet = new Pet(race, dateOfBirth, isVaccinated, ownerName);
        petRepository.save(pet);
    }

    @Override
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    @Override
    public Optional<Pet> getPetByID(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be greater than zero");
        }
        return petRepository.findById(id);
    }

    @Override
    public void deletePetById(long id) throws EntityUpdateFailedException, EntityNotFoundException {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be greater than zero");
        }
        Optional<Pet> optionalPet = petRepository.findById(id);

        if (optionalPet.isPresent()) {
            petRepository.delete(optionalPet.get());
        } else {
            throw new EntityNotFoundException("Pet ID not found");
        }
    }
}
