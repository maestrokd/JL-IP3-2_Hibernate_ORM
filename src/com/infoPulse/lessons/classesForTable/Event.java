package com.infoPulse.lessons.classesForTable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "event")
public class Event implements Serializable {

    // Fields

    @Id
    @ManyToOne
//    @JoinColumn(name = "customer_id", foreignKey = @ForeignKey(name = "fk_customer_customer_services"))
    private Customer customer;

    @Id
    @ManyToOne
//    @JoinColumn(name = "service_id", foreignKey = @ForeignKey(name = "fk_service_customer_services"))
    private Service service;

    @Id
    @Column(name = "event_date")
    private Date eventDate;


    @Column(name = "event_cost")
    private float eventCost;


    // Getters and Setters


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

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public float getEventCost() {
        return eventCost;
    }

    public void setEventCost(float eventCost) {
        this.eventCost = eventCost;
    }
}
