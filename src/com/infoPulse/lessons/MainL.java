package com.infoPulse.lessons;

import com.infoPulse.lessons.DaoTools.Dao;
import com.infoPulse.lessons.DaoTools.DatabaseConnectorEManager;
import com.infoPulse.lessons.classesForTable.*;

import java.util.List;

public class MainL {

    public static void main(String[] args) {

//        MainL mainL = new MainL();
//        mainL.insertContentForTest();

        Dao dao = new Dao();


        // Show list of services for customer
//        Customer customerTemp = dao.getCustomerForNumber("067-231-00-45");
//        dao.getServicesForCustomerTest(customerTemp);
//        List<Service> serviceList = dao.getServicesForCustomer(customerTemp);
//        for (Service service : serviceList) {
//            System.out.println(customerTemp.getPhone_number() + "|" + service.getServiceName());
//        }


        // Add event for customer
        Customer customerTempEvent = dao.getCustomerForNumber("067-231-00-45");
        customerTempEvent.addEvent(dao.getServiceForName("mms"));
        dao.updateEntity(customerTempEvent);


        // Get and show event list for period for Customer
//        List<Event> eventList = dao.getEventsForCustomer(customerTempEvent, "2017-11-29 21:40:00", "2017-11-29 21:50:00");
//        for (Event event : eventList) {
//            System.out.println(event.getCustomer().getPhoneNumber() + "|" +
//                    event.getService().getName() + "|" +event.getDate());
//        }


        // Get and show amount for period for Customer
//        System.out.println(dao.getAmountForPeriodForCustomer(customerTempEvent, "2017-11-29 00:00:00", "2017-11-30 00:00:00"));


        // Create new bill for period for Customer
        customerTempEvent.addBill("2017-11-30 00:00:00", "2017-12-01 00:00:00", dao);
        dao.updateEntity(customerTempEvent);


        // Show billList for Customer
        for (Bill bill : customerTempEvent.getBillList()) {
            System.out.println(bill.getId() + "|" + bill.getCustomer().getPhoneNumber() + "|" + bill.getAmount());
        }

        // Old version to close
//        dao.endDao();

        // New version to close
        DatabaseConnectorEManager.getInstance().closeManagerAndFactory();
    }

    public void insertContentForTest() {

        Dao dao = new Dao();

        // Add new users
        User user1 = new User();
        user1.setLogin("user1");
        user1.setPassword("pass1");
        dao.addEntity(user1);

        User user2 = new User();
        user2.setLogin("user2");
        user2.setPassword("pass2");
        dao.addEntity(user2);


        // Add new services
        Service serviceSMS = new Service();
        serviceSMS.setName("sms");
        serviceSMS.setPayroll(0.3f);
        dao.addEntity(serviceSMS);

        Service serviceMMS = new Service();
        serviceMMS.setName("mms");
        serviceMMS.setPayroll(0.7f);
        dao.addEntity(serviceMMS);

        Service serviceInternet = new Service();
        serviceInternet.setName("internet");
        serviceInternet.setPayroll(10f);
        dao.addEntity(serviceInternet);


        // Add new serviceStatuses
        ServiceStatus serviceStatus1 = new ServiceStatus();
        dao.addEntity(serviceStatus1);
        ServiceStatus serviceStatus2 = new ServiceStatus();
        serviceStatus2.setName("active");
        dao.addEntity(serviceStatus2);


        // Add new customers
        Customer customer1 = new Customer();
        customer1.setUser(user1);
        customer1.setPhoneNumber("067-231-00-45");
        customer1.setStatus("Active");
        customer1.setBalance(350);
        customer1.addService(serviceSMS, serviceStatus2);
        dao.addEntity(customer1);

        Customer customer2 = new Customer();
        customer2.setUser(user1);
        customer2.setPhoneNumber("097-231-00-46");
        customer2.setStatus("Active");
        customer2.setBalance(150);
        customer2.addService(serviceSMS, serviceStatus1);
        dao.addEntity(customer2);

        customer2.getCustomerServicesList().get(0).setServiceStatus(serviceStatus2);


        // Add service to customer from database
//        Customer customerTemp = dao.getCustomerForNumber("067-231-00-45");
//        System.out.println(customerTemp.getPhoneNumber());
//        Service serviceTemp = dao.getServiceForName("mms");
//        System.out.println(serviceTemp.getName());
//        customerTemp.addService(serviceTemp, dao.getServiceStatusForName("deactive"));
//        serviceTemp = dao.getServiceForName("internet");
//        System.out.println(serviceTemp.getName());
//        customerTemp.addService(serviceTemp, dao.getServiceStatusForName("deactive"));
//        dao.updateEntity(customerTemp);


        // Old version to close
//        dao.endDao();
    }





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
