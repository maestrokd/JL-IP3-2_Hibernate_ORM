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
    SessionFactory sessionFactory = (SessionFactory) Persistence.createEntityManagerFactory("myconn.hibernate");
    EntityManager entityManager = sessionFactory.createEntityManager();


    public <T> void addEntity(T entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

    public Customer getCustomerForNumber(String name) {
        entityManager.getTransaction().begin();

        Query query = entityManager.createQuery("SELECT cu FROM Customer cu " + "WHERE cu.phone_number = :phone_number", Customer.class);
        query.setParameter("phone_number", name);
        Customer otherCustomer = (Customer) query.getResultList().get(0);

        entityManager.getTransaction().commit();
        return otherCustomer;

    }

    public Service getServiceForName(String name) {
        entityManager.getTransaction().begin();

        Query query = entityManager.createQuery("SELECT se FROM Service se " + "WHERE se.serviceName = :servise_name", Service.class);
        query.setParameter("servise_name", name);
        Service service = (Service) query.getResultList().get(0);

        entityManager.getTransaction().commit();
        return service;
    }

    public void getServicesForCustomerTest(Customer customer) {
        entityManager.getTransaction().begin();

        List<Object[]> objects = entityManager.createQuery("SELECT cu.phone_number, se.serviceName  FROM Customer cu " +
                "JOIN cu.customerServicesList csl ON cu.customer_id = csl.customer.customer_id " +
                "JOIN Service se ON csl.service.id = se.id " +
                "WHERE cu.customer_id = :customer_id ", Object[].class).
                setParameter("customer_id", customer.getCustomer_id()).getResultList();
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
                "JOIN cu.customerServicesList csl ON cu.customer_id = csl.customer.customer_id " +
                "JOIN Service se ON csl.service.id = se.id " +
                "WHERE cu.customer_id = :customer_id ", Service.class).
                setParameter("customer_id", customer.getCustomer_id()).getResultList();

        entityManager.getTransaction().commit();
        return serviceList;
    }

    public List<Event> getEventForCustomer(Customer customer, String startDate, String endDate) {
        entityManager.getTransaction().begin();

        List<Event> eventList = entityManager.createQuery("SELECT ev FROM Event ev " +
//                "JOIN cu.customerServicesList csl ON cu.customer_id = csl.customer.customer_id " +
//                "JOIN Service se ON csl.service.id = se.id " +
                "WHERE ev.customer.customer_id = :customer_id " +
                "AND ev.eventDate " +
                "BETWEEN STR_TO_DATE(:startDate, '%Y-%m-%d %H:%i:%s') AND  STR_TO_DATE(:endDate, '%Y-%m-%d %H:%i:%s') ",
                Event.class).
                setParameter("customer_id", customer.getCustomer_id()).
                setParameter("startDate", startDate).
                setParameter("endDate", endDate).
                getResultList();

        entityManager.getTransaction().commit();
        return eventList;
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

    public void endDao() {
        entityManager.close();
        sessionFactory.close();
    }

}
