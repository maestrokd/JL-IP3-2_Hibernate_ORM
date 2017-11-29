package com.infoPulse.lessons;

import com.infoPulse.lessons.DaoTools.Dao;
import com.infoPulse.lessons.classesForTable.*;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class MainL {

    public static void main(String[] args) {

//        MainL mainL = new MainL();
//        mainL.insertContentForTest();

        Dao dao = new Dao();




        // Add service to customer from database
//        Customer customerTemp = dao.getCustomerForNumber("067-231-00-45");
//        System.out.println(customerTemp.getCustomer_id());
//        Service serviceTemp = dao.getServiceForName("internet");
//        System.out.println(serviceTemp.getServiceName());
//        customerTemp.addService(serviceTemp, dao.getServiceStatusForName("deactive"));
//        dao.updateEntity(customerTemp);


        // Show list of services for customer
        Customer customerTemp = dao.getCustomerForNumber("067-231-00-45");
//        dao.getServicesForCustomerTest(customerTemp);
//        List<Service> serviceList = dao.getServicesForCustomer(customerTemp);
//        for (Service service : serviceList) {
//            System.out.println(customerTemp.getPhone_number() + "|" + service.getServiceName());
//        }


        // Add event for customer
        Customer customerTempEvent = dao.getCustomerForNumber("067-231-00-45");
        customerTempEvent.addEvent(dao.getServiceForName("sms"));
        dao.updateEntity(customerTemp);

        List<Event> eventList = dao.getEventForCustomer(customerTemp, "2017-11-29 11:00:00", "2017-11-30 14:00:00");

        for (Event event : eventList) {
            System.out.println(event.getCustomer().getPhone_number() + "|" +
                    event.getService().getServiceName() + "|" +event.getEventDate());
        }

        dao.endDao();

    }

    public void insertContentForTest() {

//        SessionFactory sessionFactory = (SessionFactory) Persistence.createEntityManagerFactory("myconn.hibernate");
//        EntityManager entityManager = sessionFactory.createEntityManager();

        Dao dao = new Dao();

        // Add new users
        User user1 = new User();
        user1.setUser_login("user1");
        user1.setUser_password("pass1");
        dao.addEntity(user1);

        User user2 = new User();
        user2.setUser_login("user2");
        user2.setUser_password("pass2");
        dao.addEntity(user2);


//        entityManager.getTransaction().begin();
//        Service service1 = new Service();
//        service1.setServiceName("sms");
//        service1.setServicePayroll(0.3f);
//        entityManager.persist(service1);
//        entityManager.getTransaction().commit();

        // Add new services
        Service serviceSMS = new Service();
        serviceSMS.setServiceName("sms");
        serviceSMS.setServicePayroll(0.3f);
        dao.addEntity(serviceSMS);

        Service serviceMMS = new Service();
        serviceMMS.setServiceName("mms");
        serviceMMS.setServicePayroll(0.7f);
        dao.addEntity(serviceMMS);

        Service serviceInternet = new Service();
        serviceInternet.setServiceName("internet");
        serviceInternet.setServicePayroll(10f);
        dao.addEntity(serviceInternet);



        // Add new serviceStatuses
//        entityManager.getTransaction().begin();
        ServiceStatus serviceStatus1 = new ServiceStatus();
        dao.addEntity(serviceStatus1);
        ServiceStatus serviceStatus2 = new ServiceStatus();
        serviceStatus2.setName("active");
        dao.addEntity(serviceStatus2);
//        entityManager.persist(serviceStatus1);
//        entityManager.persist(serviceStatus2);
//        entityManager.getTransaction().commit();

        // Add new customers
//        entityManager.getTransaction().begin();
        Customer customer1 = new Customer();
        customer1.setUser(user1);
        customer1.setPhone_number("067-231-00-45");
        customer1.setCustomer_status("Active");
        customer1.setCustomer_balance(350);
        customer1.addService(serviceSMS, serviceStatus2);
        dao.addEntity(customer1);

        Customer customer2 = new Customer();
        customer2.setUser(user1);
        customer2.setPhone_number("097-231-00-46");
        customer2.setCustomer_status("Active");
        customer2.setCustomer_balance(150);
        customer2.addService(serviceSMS, serviceStatus1);
        dao.addEntity(customer2);
//        entityManager.persist(customer1);
//        entityManager.persist(customer2);
//        entityManager.getTransaction().commit();

//        entityManager.getTransaction().begin();
        customer2.getCustomerServicesList().get(0).setServiceStatus(serviceStatus2);
//        entityManager.getTransaction().commit();

//        entityManager.close();
//        sessionFactory.close();
        dao.endDao();
    }

//    public void



    // Tests in univer
    public void testInUniver() {
//        SessionFactory sessionFactory = (SessionFactory) Persistence.createEntityManagerFactory("myconn.hibernate");
//        EntityManager entityManager = sessionFactory.createEntityManager();
//
//        entityManager.getTransaction().begin();
//        User user = new User();
//        user.setUser_login("usejfjyfhr54");
//        user.setUser_password("pass54");
//        entityManager.persist(user);
////        user=entityManager.find(User.class,97);
//        entityManager.getTransaction().commit();
//
//        System.out.println(user);
////        entityManager.refresh(user);
//
//        entityManager.getTransaction().begin();
//        Customer customer = new Customer();
//
////        customer.setUser_id(user.getUser_id());
//
//        customer.setUser(user);
//        customer.setPhone_number(672310045);
//        customer.setCustomer_status("Active");
//        customer.setCustomer_balance(350);
//
//        Service service = new Service();
////        service.setServiceID(1);
//        service.setServiseName("sms");
//        service.setServicePayroll(0.3f);
//        entityManager.persist(service);
//        customer.addService(service);
//
//        entityManager.persist(customer);
//
////        CustomerServices customerServices = new CustomerServices();
////        customerServices.setCustomer(customer);
////        customerServices.setService(service);
////        entityManager.persist(customerServices);
//
//
//        entityManager.getTransaction().commit();
//
//
//
////        Query query = entityManager.createQuery("from User");
////        List<User> lst = query.getResultList();
////        System.out.println(lst);
//
//        entityManager.close();
//        sessionFactory.close();
    }

}
