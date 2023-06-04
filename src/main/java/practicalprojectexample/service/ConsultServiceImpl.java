// FOLDER: service | ConsultServiceImpl.java

package practicalprojectexample.service;

import practicalprojectexample.model.Consult;
import practicalprojectexample.model.Pet;
import practicalprojectexample.model.Vet;
import practicalprojectexample.repository.ConsultRepository;
import practicalprojectexample.repository.PetRepository;
import practicalprojectexample.repository.VetRepository;
import practicalprojectexample.repository.exception.EntityUpdateFailedException;
import practicalprojectexample.service.exception.EntityNotFoundException;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class ConsultServiceImpl implements ConsultService {

    private final VetRepository vetRepository;
    private final PetRepository petRepository;
    private final ConsultRepository consultRepository;

    public ConsultServiceImpl(VetRepository vetRepository, PetRepository petRepository, ConsultRepository consultRepository) {
        this.vetRepository = vetRepository;
        this.petRepository = petRepository;
        this.consultRepository = consultRepository;
    }

    @Override
    public void createConsult(long vetId, long petId, Date dateOfConsult, String description) throws EntityNotFoundException, EntityUpdateFailedException {
        if (vetId <= 0) {
            throw new IllegalArgumentException("Invalid Vet ID. Vet ID value must be greater than zero.");
        }
        if (petId <= 0) {
            throw new IllegalArgumentException("Invalid Vet ID. Vet ID value must be greater than zero.");
        }
        if (dateOfConsult == null) {
            throw new IllegalArgumentException("Invalid date value. Date cannot be null.");
        }
        if (dateOfConsult.before(Date.from(Instant.now().minus(Duration.ofDays(1))))) {
            throw new IllegalArgumentException("Invalid date value. Date cannot be in the past.");
        }
        if (description == null || description.isBlank() || description.isEmpty()) {
            throw new IllegalArgumentException("Invalid description. Description value must not be null and not blank");
        }

        Optional<Vet> optionalVet = vetRepository.findById(vetId);
        if (optionalVet.isEmpty()) {
            throw new EntityNotFoundException("Vet ID " + vetId + " was not found.");
        }

        Optional<Pet> optionalPet = petRepository.findById(petId);
        if (optionalPet.isEmpty()) {
            throw new EntityNotFoundException("Pet ID " + petId + " was not found.");
        }

        Consult consult = new Consult(dateOfConsult, description);
        consult.setVet(optionalVet.get());
        consult.setPet(optionalPet.get());
        consultRepository.save(consult);
    }

    @Override
    public List<Consult> getAllConsults() {
        return consultRepository.findAll();
    }

    @Override
    public Optional<Consult> getConsultById(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid Consult ID. Consult ID value must be greater than zero.");
        }
        return consultRepository.findById(id);
    }

    @Override
    public void updateConsultById(long id, String description) throws EntityNotFoundException, EntityUpdateFailedException {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid Consult ID. Consult ID value must be greater than zero.");
        }
        if (description == null || description.isBlank() || description.isEmpty()) {
            throw new IllegalArgumentException("Invalid description. Description value must not be null and not blank");
        }
        Optional<Consult> optionalConsult = consultRepository.findById(id);
        if (optionalConsult.isEmpty()) {
            throw new EntityNotFoundException("Consult ID not found");
        }
        Consult consult = optionalConsult.get();
        consult.setDescription(description);
        consultRepository.update(consult);
    }
}
