package com.bookstore.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;
    private String fullName;
    private Date dateOfPublishing;
    private double inputPrice;
    private double sellingPrice;
    private int quantity;
    private String language;
    private String description;
    private String imagePath;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "authorId")
    private Author author;

    @OneToMany(mappedBy="book",cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    private List<DetailOrder> listDetailOrder = new ArrayList<DetailOrder>();

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "book_typeOfBook", joinColumns = {
        @JoinColumn(name = "bookId")}, inverseJoinColumns = {
        @JoinColumn(name = "typeOfBookId")})
    private Set<TypeOfBook> typeOfBook = new HashSet<>();
    
    

    public Book() {
    }

    public Book(Long bookId, String fullName, Date dateOfPublishing, double inputPrice, double sellingPrice, int quantity, String language, String description, Author author) {
        this.bookId = bookId;
        this.fullName = fullName;
        this.dateOfPublishing = dateOfPublishing;
        this.inputPrice = inputPrice;
        this.sellingPrice = sellingPrice;
        this.quantity = quantity;
        this.language = language;
        this.description = description;
        this.author = author;
    }

    public Book(Long bookId, String fullName, Date dateOfPublishing, double inputPrice, double sellingPrice, int quantity, String language, String description) {
        this.bookId = bookId;
        this.fullName = fullName;
        this.dateOfPublishing = dateOfPublishing;
        this.inputPrice = inputPrice;
        this.sellingPrice = sellingPrice;
        this.quantity = quantity;
        this.language = language;
        this.description = description;

    }

    public Book(String fullName, Date dateOfPublishing, double inputPrice, double sellingPrice, int quantity, String language, String description,String imagePath) {
        this.imagePath = imagePath;
        this.fullName = fullName;
        this.dateOfPublishing = dateOfPublishing;
        this.inputPrice = inputPrice;
        this.sellingPrice = sellingPrice;
        this.quantity = quantity;
        this.language = language;
        this.description = description;
    }
    
    public Book(String fullName, Date dateOfPublishing, double inputPrice, double sellingPrice, int quantity, String language, String description) {
       
        this.fullName = fullName;
        this.dateOfPublishing = dateOfPublishing;
        this.inputPrice = inputPrice;
        this.sellingPrice = sellingPrice;
        this.quantity = quantity;
        this.language = language;
        this.description = description;
    }

    public Book(Long bookId, String fullName, Date dateOfPublishing, double inputPrice, double sellingPrice, int quantity, String language, String description, String imagePath) {
        this.bookId = bookId;
        this.fullName = fullName;
        this.dateOfPublishing = dateOfPublishing;
        this.inputPrice = inputPrice;
        this.sellingPrice = sellingPrice;
        this.quantity = quantity;
        this.language = language;
        this.description = description;
        this.imagePath = imagePath;
        
    }

  
  

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    
    

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getDateOfPublishing() {
        return dateOfPublishing;
    }

    public void setDateOfPublishing(Date dateOfPublishing) {
        this.dateOfPublishing = dateOfPublishing;
    }

    public double getInputPrice() {
        return inputPrice;
    }

    public void setInputPrice(double inputPrice) {
        this.inputPrice = inputPrice;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Set<TypeOfBook> getTypeOfBook() {
        return typeOfBook;
    }

    public void setTypeOfBook(Set<TypeOfBook> typeOfBook) {
        this.typeOfBook = typeOfBook;
    }

    public void addTypeOfBook(TypeOfBook type) {
        this.typeOfBook.add(type);

    }

    public List<DetailOrder> getListDetailOrder() {
        return listDetailOrder;
    }

    public void setListDetailOrder(List<DetailOrder> listDetailOrder) {
        this.listDetailOrder = listDetailOrder;
    }

    @Override
    public String toString() {
        return "Book{" + "bookId=" + bookId + ", fullName=" + fullName + ", dateOfPublishing=" + dateOfPublishing + ", inputPrice=" + inputPrice + ", sellingPrice=" + sellingPrice + ", quantity=" + quantity + ", language=" + language + ", description=" + description + '}';
    }

}
