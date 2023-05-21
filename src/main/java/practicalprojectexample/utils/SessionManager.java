package practicalprojectexample.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import practicalprojectexample.model.Consult;
import practicalprojectexample.model.Pet;
import practicalprojectexample.model.Vet;

public class SessionManager extends AbstractSessionManager {

    // Singleton with eager initialization:
    private static final SessionManager INSTANCE = new SessionManager();
    // In order for it to be a Singleton, it needs a private constructor:
    private SessionManager() {

    }

    public static SessionFactory getSessionFactory()
    {
        return INSTANCE.getSessionFactory("pet_clinic");
    }

    public static void shutdown()
    {
        INSTANCE.shutdownSessionManager();
    }

    @Override
    protected void setAnnotatedClasses(Configuration configuration) {
        configuration.addAnnotatedClass(Vet.class);
        configuration.addAnnotatedClass(Pet.class);
        configuration.addAnnotatedClass(Consult.class);
    }
}
