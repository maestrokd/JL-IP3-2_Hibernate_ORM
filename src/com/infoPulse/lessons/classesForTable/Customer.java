package com.infoPulse.lessons.classesForTable;


import com.infoPulse.lessons.DaoTools.Dao;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
@Table(name = "customer")
public class Customer {


    // Fields
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "customer_id")
//    private int customer_id;

//    @Column(name = "user_id")
//    private int user_id;

    @Id
    @Column(name = "phone_number", length = 25)
    private String phoneNumber = null;

    @Column(name = "status")
    private String status;

    @Column(name = "balance")
    private int balance;

    @ManyToOne
    @JoinColumn(name = "user_login", foreignKey = @ForeignKey(name = "fk_user_customer"))
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
        event.setDate(new Date());
        event.setCost(service.getPayroll());
        eventList.add(event);
        service.getEventList().add(event);
    }


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = false, mappedBy = "customer")
    private List<Bill> billList = new ArrayList<>();


    public  void addBill(String startData, String endData, Dao dao) {
//        Dao dao = new  Dao();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-mm-dd HH:mm:ss");
        Date parsingStartDate = null;
        Date parsingEndDate = null;
        try {
            parsingStartDate = ft.parse(startData);
            parsingEndDate = ft.parse(endData);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Bill bill = new Bill();
        bill.setCustomer(this);
        bill.setStartDate(parsingStartDate);
        bill.setEndDate(parsingEndDate);
        bill.setAmount(dao.getAmountForPeriodForCustomer(this, startData, endData));
        billList.add(bill);
//        dao.endDao();
    }


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = false, mappedBy = "customer")
    private List<Payment> paymentList = new ArrayList<>();


    // Constructors
    public Customer() {}


    // Getters and Setters

//    public int getCustomer_id() {
//        return customer_id;
//    }
//
//    public void setCustomer_id(int customer_id) {
//        this.customer_id = customer_id;
//    }

//    public int getUser_id() {
//        return user_id;
//    }
//
//    public void setUser_id(int user_id) {
//        this.user_id = user_id;
//    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
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

    public List<Bill> getBillList() {
        return billList;
    }

    public void setBillList(List<Bill> billList) {
        this.billList = billList;
    }

    public List<Payment> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(List<Payment> paymentList) {
        this.paymentList = paymentList;
    }


    // Methods




}
