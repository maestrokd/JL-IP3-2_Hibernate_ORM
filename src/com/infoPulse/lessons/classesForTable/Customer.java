package com.infoPulse.lessons.classesForTable;


import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "customer")
public class Customer {


    // Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private int customer_id;

//    @Column(name = "user_id")
//    private int user_id;

    @Column(name = "phone_number")
    private String phone_number;

    @Column(name = "customer_status")
    private String customer_status;

    @Column(name = "customer_balance")
    private int customer_balance;

    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_user_customer"))
    private User user;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<CustomerServices> customerServicesList = new ArrayList<>();

    public  void addService(Service service, ServiceStatus serviceStatus) {
        CustomerServices customerServices = new CustomerServices();
        customerServices.setCustomer(this);
        customerServices.setService(service);
        customerServices.setServiceStatus(serviceStatus);
        customerServicesList.add(customerServices);
        service.getCustomerServicesList().add(customerServices);
    }

//    @ManyToMany
//    @JoinTable(name = "cus_ser",
//            joinColumns = {@JoinColumn(name = "customer_id")},
//            inverseJoinColumns = {@JoinColumn(name = "servise_id")})
//    private List<Service> serviceList = new ArrayList<>();


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = false, mappedBy = "customer")
    private Set<Event> eventList = new HashSet<>();

    public void addEvent(Service service) {
        Event event = new Event();
        event.setCustomer(this);
        event.setService(service);
        event.setEventDate(new Date());
        event.setEventCost(service.getServicePayroll());
        eventList.add(event);
        service.getEventList().add(event);
    }


    // Constructors
    public Customer() {}


    // Getter and Setter
    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

//    public int getUser_id() {
//        return user_id;
//    }
//
//    public void setUser_id(int user_id) {
//        this.user_id = user_id;
//    }


    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getCustomer_status() {
        return customer_status;
    }

    public void setCustomer_status(String customer_status) {
        this.customer_status = customer_status;
    }

    public int getCustomer_balance() {
        return customer_balance;
    }

    public void setCustomer_balance(int customer_balance) {
        this.customer_balance = customer_balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    //    public List<Service> getServiceList() {
//        return serviceList;
//    }
//
//    public void setServiceList(List<Service> serviceList) {
//        this.serviceList = serviceList;
//    }

    // Methods

    //    @Override
//    public String toString() {
//        return "Customer{" +
//                "customer_id=" + customer_id +
//                ", user_id=" + user_id +
//                ", phone_number=" + phone_number +
//                ", customer_status='" + customer_status + '\'' +
//                ", customer_balance=" + customer_balance +
//                '}';
//    }


}
