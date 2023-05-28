// FOLDER: repository | PetRepositoryImpl.java

package practicalprojectexample.repository;

import practicalprojectexample.model.Pet;
import practicalprojectexample.repository.base.RepositoryImpl;

public class PetRepositoryImpl extends RepositoryImpl<Pet> implements PetRepository {
    public PetRepositoryImpl() {
        super(Pet.class);
    }
}
