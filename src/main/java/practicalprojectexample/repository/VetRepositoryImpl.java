// FOLDER: repository | VetRepositoryImpl.java

package practicalprojectexample.repository;

import practicalprojectexample.model.Vet;
import practicalprojectexample.repository.base.RepositoryImpl;

public class VetRepositoryImpl extends RepositoryImpl<Vet> implements VetRepository {

    public VetRepositoryImpl() {
        super(Vet.class);
    }
}
