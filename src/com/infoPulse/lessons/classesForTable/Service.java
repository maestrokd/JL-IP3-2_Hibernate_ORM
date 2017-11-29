package com.infoPulse.lessons.classesForTable;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "service")
public class Service {

    // Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    private int serviceID;

    @Column(name = "service_name")
    private String serviceName;

    @Column(name = "service_payroll")
    private float servicePayroll;

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
        event.setEventDate(new Date());
        event.setEventCost(this.servicePayroll);
        eventList.add(event);
        customer.getEventList().add(event);
    }


    // Constructors
    public Service() {
    }


    // Getters and Setters
    public int getServiceID() {
        return serviceID;
    }

    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviseName) {
        this.serviceName = serviseName;
    }

    public float getServicePayroll() {
        return servicePayroll;
    }

    public void setServicePayroll(float servicePayroll) {
        this.servicePayroll = servicePayroll;
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

    //
//    // Methods


}
