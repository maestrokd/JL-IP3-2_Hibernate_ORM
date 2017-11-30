package com.infoPulse.lessons.DaoTools;

import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class DatabaseConnectorEManager {
    private SessionFactory sessionFactory;
    private EntityManager entityManager;
    private static DatabaseConnectorEManager ourInstance = new DatabaseConnectorEManager();

    public static DatabaseConnectorEManager getInstance() {
        return ourInstance;
    }

    private DatabaseConnectorEManager() {
        this.sessionFactory = (SessionFactory) Persistence.createEntityManagerFactory("myconn.hibernate");
        this.entityManager = sessionFactory.createEntityManager();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void closeManagerAndFactory() {
        entityManager.close();
        sessionFactory.close();
    }
}
