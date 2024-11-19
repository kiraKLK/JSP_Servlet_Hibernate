
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
import javax.persistence.OneToMany;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerID;
    private String fullName;
    private String userName;
    private String password;
    private String gender;
    private String address;
    private String email;
    private boolean registerGetAdv;
    private String verifyCode;
    private Date timeVerify;
    private boolean verifyState;
    private String pathAvatarImages;
    
    @OneToMany(mappedBy="customer",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<CustomerOrder> order = new ArrayList<>();

    public Customer() {
    }

    public Customer(Long customerID, String fullName, String userName, String password, String gender, String address, String email, boolean registerGetAdv, String verifyCode, Date timeVerify, boolean verifyState, String pathAvatarImages) {
        this.customerID = customerID;
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
        this.gender = gender;
        this.address = address;
        this.email = email;
        this.registerGetAdv = registerGetAdv;
        this.verifyCode = verifyCode;
        this.timeVerify = timeVerify;
        this.verifyState = verifyState;
        this.pathAvatarImages = pathAvatarImages;
    }

    public Customer( String fullName, String userName, String password, String gender, String address, String email, boolean registerGetAdv) {
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
        this.gender = gender;
        this.address = address;
        this.email = email;
        this.registerGetAdv = registerGetAdv;
    }
    
    
    
    

    public Long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isRegisterGetAdv() {
        return registerGetAdv;
    }

    public void setRegisterGetAdv(boolean registerGetAdv) {
        this.registerGetAdv = registerGetAdv;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public Date getTimeVerify() {
        return timeVerify;
    }

    public void setTimeVerify(Date timeVerify) {
        this.timeVerify = timeVerify;
    }

    public boolean isVerifyState() {
        return verifyState;
    }

    public void setVerifyState(boolean verifyState) {
        this.verifyState = verifyState;
    }

    public String getPathAvatarImages() {
        return pathAvatarImages;
    }

    public void setPathAvatarImages(String pathAvatarImages) {
        this.pathAvatarImages = pathAvatarImages;
    }

    public List<CustomerOrder> getOrder() {
        return order;
    }

    public void setOrder(List<CustomerOrder> order) {
        this.order = order;
    }
    
    
    public void addOrder(CustomerOrder o) {
        this.order.add(o);
    }
    
}
