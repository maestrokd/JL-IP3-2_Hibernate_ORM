package com.infoPulse.lessons.classesForTable;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "customer_services")
public class CustomerServices implements Serializable {


    // Fields
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "customer_services_id")
//    private int customeServicesID;

    @Id
    @ManyToOne
//    @JoinColumn(name = "customer_id", foreignKey = @ForeignKey(name = "fk_customer_customer_services"))
    private Customer customer;

    @Id
    @ManyToOne
//    @JoinColumn(name = "service_id", foreignKey = @ForeignKey(name = "fk_service_customer_services"))
    private Service service;

    @ManyToOne
    @JoinColumn(name = "service_status_id", foreignKey = @ForeignKey(name = "fk_service_status_customer_services"))
    private ServiceStatus serviceStatus;

    // Constructors
    public CustomerServices() {
    }


    // Getters and Setters

//    public int getCustomeServicesID() {
//        return customeServicesID;
//    }
//
//    public void setCustomeServicesID(int customeServicesID) {
//        this.customeServicesID = customeServicesID;
//    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public ServiceStatus getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(ServiceStatus serviceStatus) {
        this.serviceStatus = serviceStatus;
    }
}
