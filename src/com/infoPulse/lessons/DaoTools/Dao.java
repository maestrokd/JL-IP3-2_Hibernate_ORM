package com.infoPulse.lessons.DaoTools;

import com.infoPulse.lessons.classesForTable.Customer;
import com.infoPulse.lessons.classesForTable.Event;
import com.infoPulse.lessons.classesForTable.Service;
import com.infoPulse.lessons.classesForTable.ServiceStatus;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class Dao {

    // Fields

    // Old version to create
//    SessionFactory sessionFactory = (SessionFactory) Persistence.createEntityManagerFactory("myconn.hibernate");
//    EntityManager entityManager = sessionFactory.createEntityManager();

    EntityManager entityManager;


    // Constructors
    public Dao() {
        this.entityManager = DatabaseConnectorEManager.getInstance().getEntityManager();
    }


    // Methods
    public <T> void addEntity(T entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

    public Customer getCustomerForNumber(String name) {
        entityManager.getTransaction().begin();

        Query query = entityManager.createQuery("SELECT cu FROM Customer cu " + "WHERE cu.phoneNumber = :phone_number", Customer.class);
        query.setParameter("phone_number", name);
        Customer otherCustomer = (Customer) query.getResultList().get(0);

        entityManager.getTransaction().commit();
        return otherCustomer;
    }

    public Service getServiceForName(String name) {
        entityManager.getTransaction().begin();

        Query query = entityManager.createQuery("SELECT se FROM Service se " + "WHERE se.name = :servise_name", Service.class);
        query.setParameter("servise_name", name);
        Service service = (Service) query.getResultList().get(0);

        entityManager.getTransaction().commit();
        return service;
    }

    public void getServicesForCustomerTest(Customer customer) {
        entityManager.getTransaction().begin();

        List<Object[]> objects = entityManager.createQuery("SELECT cu.phoneNumber, se.name  FROM Customer cu " +
                "JOIN cu.customerServicesList csl ON cu.phoneNumber = csl.customer.phoneNumber " +
                "JOIN Service se ON csl.service.name = se.name " +
                "WHERE cu.phoneNumber = :phone_number ", Object[].class).
                setParameter("phone_number", customer.getPhoneNumber()).getResultList();
//        query.setParameter("customer_id", customer.getCustomer_id());
//        List<Object[]> objects = query.getResultList();

        for (Object[] obj : objects) {
            System.out.println(obj[0].toString() + "|" + obj[1].toString());
        }

        entityManager.getTransaction().commit();
    }

    public List<Service> getServicesForCustomer(Customer customer) {
        entityManager.getTransaction().begin();

        List<Service> serviceList = entityManager.createQuery("SELECT se FROM Customer cu " +
                "JOIN cu.customerServicesList csl ON cu.phoneNumber = csl.customer.phoneNumber " +
                "JOIN Service se ON csl.service.name = se.name " +
                "WHERE cu.phoneNumber = :phone_number ", Service.class).
                setParameter("phone_number", customer.getPhoneNumber()).getResultList();

        entityManager.getTransaction().commit();
        return serviceList;
    }

    public List<Event> getEventsForCustomer(Customer customer, String startDate, String endDate) {
        entityManager.getTransaction().begin();

        List<Event> eventList = entityManager.createQuery("SELECT ev FROM Event ev " +
//                "JOIN cu.customerServicesList csl ON cu.customer_id = csl.customer.customer_id " +
//                "JOIN Service se ON csl.service.id = se.id " +
                "WHERE ev.customer.phoneNumber = :phone_number " +
                "AND ev.date " +
                "BETWEEN STR_TO_DATE(:startDate, '%Y-%m-%d %H:%i:%s') AND  STR_TO_DATE(:endDate, '%Y-%m-%d %H:%i:%s') ",
                Event.class).
                setParameter("phone_number", customer.getPhoneNumber()).
                setParameter("startDate", startDate).
                setParameter("endDate", endDate).
                getResultList();

        entityManager.getTransaction().commit();
        return eventList;
    }


    public float getAmountForPeriodForCustomer(Customer customer, String startDate, String endDate) {
        entityManager.getTransaction().begin();

        Object obj = entityManager.createQuery("SELECT SUM(ev.cost) FROM Event ev " +
                        "WHERE ev.customer.phoneNumber = :phone_number " +
                        "AND ev.date " +
                        "BETWEEN STR_TO_DATE(:startDate, '%Y-%m-%d %H:%i:%s') AND  STR_TO_DATE(:endDate, '%Y-%m-%d %H:%i:%s') ",
                Object.class).
                setParameter("phone_number", customer.getPhoneNumber()).
                setParameter("startDate", startDate).
                setParameter("endDate", endDate).
                getResultList().get(0);

//        System.out.println(obj.toString());

        entityManager.getTransaction().commit();
        return Float.valueOf(obj.toString());
    }


    public ServiceStatus getServiceStatusForName(String name) {
        entityManager.getTransaction().begin();

        Query query = entityManager.createQuery("SELECT ss FROM ServiceStatus ss " + "WHERE ss.name = :service_status_name", ServiceStatus.class);
        query.setParameter("service_status_name", name);
        ServiceStatus serviceStatus = (ServiceStatus) query.getResultList().get(0);

        entityManager.getTransaction().commit();
        return serviceStatus;
    }


    public <T> void updateEntity(T entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

    // Old version to close
//    public void endDao() {
//        entityManager.close();
//        sessionFactory.close();
//    }

}
