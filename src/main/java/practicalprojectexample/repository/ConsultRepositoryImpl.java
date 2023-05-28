// FOLDER: repository | ConsultRepositoryImpl.java

package practicalprojectexample.repository;

import practicalprojectexample.model.Consult;
import practicalprojectexample.repository.ConsultRepository;
import practicalprojectexample.repository.base.RepositoryImpl;

public class ConsultRepositoryImpl extends RepositoryImpl<Consult> implements ConsultRepository {
    public ConsultRepositoryImpl() {
        super(Consult.class);
    }
}
