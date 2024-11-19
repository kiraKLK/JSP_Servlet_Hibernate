package com.bookstore.model;

import java.util.ArrayList;
import java.util.HashSet;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;
import java.util.Set;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class DetailOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deteilOrderID;
    private Integer quantity;
    private Double priceOnBook;
    private Double priceSell;
    private Double sumMoney;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "orderID")
    private CustomerOrder order;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "bookId")
    private Book book ;

    public DetailOrder() {
    }

    public DetailOrder(Long deteilOrderID, Integer quantity, Double priceOnBook, Double priceSell, Double sumMoney, CustomerOrder order) {
        this.deteilOrderID = deteilOrderID;
        this.quantity = quantity;
        this.priceOnBook = priceOnBook;
        this.priceSell = priceSell;
        this.sumMoney = sumMoney;
        this.order = order;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }


    public Long getDeteilOrderID() {
        return deteilOrderID;
    }

    public void setDeteilOrderID(Long deteilOrderID) {
        this.deteilOrderID = deteilOrderID;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPriceOnBook() {
        return priceOnBook;
    }

    public void setPriceOnBook(Double priceOnBook) {
        this.priceOnBook = priceOnBook;
    }

    public Double getPriceSell() {
        return priceSell;
    }

    public void setPriceSell(Double priceSell) {
        this.priceSell = priceSell;
    }

    public Double getSumMoney() {
        return sumMoney;
    }

    public void setSumMoney(Double sumMoney) {
        this.sumMoney = sumMoney;
    }

    public CustomerOrder getOrder() {
        return order;
    }

    public void setOrder(CustomerOrder order) {
        this.order = order;
    }

}
