// FOLDER: repository/base | Repository.java

package practicalprojectexample.repository.base;

import practicalprojectexample.repository.exception.EntityUpdateFailedException;

import java.util.List;
import java.util.Optional;

public interface Repository <T> {

    void save(T entity) throws EntityUpdateFailedException;

    void update(T entity) throws EntityUpdateFailedException;

    void delete(T entity) throws EntityUpdateFailedException;

    Optional<T> findById(long id);   // Optional, because the IDs are unique

    List<T> findAll();
}
