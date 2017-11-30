package com.infoPulse.lessons.classesForTable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "service_status")
public class ServiceStatus {

    // Fields
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "service_status_id")
//    private int id;

    @Id
    @Column(name = "name")
    private String name = "deactive";

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = false, mappedBy = "serviceStatus")
    private Set<CustomerServices> customerServicesList = new HashSet<>();


    // Getter and Setter

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<CustomerServices> getCustomerServicesList() {
        return customerServicesList;
    }

    public void setCustomerServicesList(Set<CustomerServices> customerServicesList) {
        this.customerServicesList = customerServicesList;
    }
}
