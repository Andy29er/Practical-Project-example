// FOLDER: repository/base | RepositoryImpl.java

package practicalprojectexample.repository.base;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import practicalprojectexample.repository.exception.EntityUpdateFailedException;
import practicalprojectexample.utils.SessionManager;

import java.util.List;
import java.util.Optional;

public class RepositoryImpl<T> implements Repository<T> {

    private final Class<T> entityClass;

    public RepositoryImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public void save(T entity) throws EntityUpdateFailedException {
        Transaction transaction = null;
        // try with resources
        try (Session session = SessionManager.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(entity);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            // e.printStackTrace();  // if we don't put this, it doesn't return anything, but...*
            throw new EntityUpdateFailedException("Save failed for " + entityClass.getName(), e);
        }
    }

    @Override
    public void update(T entity) throws EntityUpdateFailedException {
        Transaction transaction = null;
        // try with resources
        try (Session session = SessionManager.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.update(entity);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            // e.printStackTrace();  // if we don't put this, it doesn't return anything,
            // but we can't put it here, and we make a new exception class and throw a new exception
            throw new EntityUpdateFailedException("Update failed for " + entityClass.getName(), e);
        }
    }

    @Override
    public void delete(T entity) throws EntityUpdateFailedException {
        Transaction transaction = null;
        // try with resources
        try (Session session = SessionManager.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.delete(entity);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            // e.printStackTrace();  // if we don't put this, it doesn't return anything, but...*
            throw new EntityUpdateFailedException("Delete failed for " + entityClass.getName(), e);
        }
    }

    @Override
    public Optional<T> findById(long id) {
        try (Session session = SessionManager.getSessionFactory().openSession()) {
            return Optional.ofNullable(session.find(entityClass, id));
        }
    }

    @Override
    public List<T> findAll() {
        try (Session session = SessionManager.getSessionFactory().openSession()) {
            // entityClass will be for Pet, Vet, or Consult
            Query<T> query = session.createQuery("from " + entityClass.getSimpleName());
            return query.list();
        }
    }
}
