
package com.bookstore.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class CustomerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderID;
    private String shipAddress;
    private String orderStatus;
    private String paymentMethod;
    private Date orderDate; 
    
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name="customer_id")
    private Customer customer;
    
    @OneToMany(mappedBy="order",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<DetailOrder> detaiOrder = new ArrayList<DetailOrder>();

    public CustomerOrder() {
    }

    public CustomerOrder(Long orderID, String shipAddress, String orderStatus, String paymentMethod, Date orderDate, Customer customer, List<DetailOrder> detaiOrder) {
        this.orderID = orderID;
        this.shipAddress = shipAddress;
        this.orderStatus = orderStatus;
        this.paymentMethod = paymentMethod;
        this.orderDate = orderDate;
        this.customer = customer;
        this.detaiOrder = detaiOrder;
    }

    public Long getOrderID() {
        return orderID;
    }

    public void setOrderID(Long orderID) {
        this.orderID = orderID;
    }

    public String getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<DetailOrder> getDetaiOrder() {
        return detaiOrder;
    }

    public void setDetaiOrder(List<DetailOrder> detaiOrder) {
        this.detaiOrder = detaiOrder;
    }
    
    
}
