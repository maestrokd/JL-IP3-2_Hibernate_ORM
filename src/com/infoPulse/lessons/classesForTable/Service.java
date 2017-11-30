package com.infoPulse.lessons.classesForTable;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "service")
public class Service {

    // Fields
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "service_id")
//    private int serviceID;

    @Id
    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "payroll")
    private float payroll;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = false, mappedBy = "customer")
    private List<CustomerServices> customerServicesList = new ArrayList<>();

    public void addCustomer(Customer customer, ServiceStatus serviceStatus) {
        CustomerServices customerServices = new CustomerServices();
        customerServices.setServiceStatus(serviceStatus);
        customerServices.setCustomer(customer);
        customerServices.setService(this);
        customerServicesList.add(customerServices);
        customer.getCustomerServicesList().add(customerServices);
    }

//    @ManyToMany(mappedBy = "serviceList")
//    private List<Customer> customerList = new ArrayList<>();


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = false, mappedBy = "customer")
    private Set<Event> eventList = new HashSet<>();

    public void addEvent(Customer customer) {
        Event event = new Event();
        event.setCustomer(customer);
        event.setService(this);
        event.setDate(new Date());
        event.setCost(this.payroll);
        eventList.add(event);
        customer.getEventList().add(event);
    }


    // Constructors
    public Service() {
    }


    // Getters and Setters

//    public int getServiceID() {
//        return serviceID;
//    }
//
//    public void setServiceID(int serviceID) {
//        this.serviceID = serviceID;
//    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPayroll() {
        return payroll;
    }

    public void setPayroll(float payroll) {
        this.payroll = payroll;
    }

    public List<CustomerServices> getCustomerServicesList() {
        return customerServicesList;
    }

    public void setCustomerServicesList(List<CustomerServices> customerServicesList) {
        this.customerServicesList = customerServicesList;
    }

    public Set<Event> getEventList() {
        return eventList;
    }

    public void setEventList(Set<Event> eventList) {
        this.eventList = eventList;
    }


    // Methods


}
