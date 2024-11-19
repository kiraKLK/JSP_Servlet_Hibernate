package com.bookstore.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class TypeOfBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long typeOfBookId;
    private String typeName;
    @ManyToMany(mappedBy = "typeOfBook", cascade = CascadeType.ALL)
    private Set<Book> listBook = new HashSet<>();

    public TypeOfBook() {
    }

    public TypeOfBook(Long typeOfBookId, String typeName, Set<Book> book) {
        this.typeOfBookId = typeOfBookId;
        this.typeName = typeName;
        this.listBook = book;
    }

    public Long getTypeOfBookId() {
        return typeOfBookId;
    }

    public void setTypeOfBookId(Long typeOfBookId) {
        this.typeOfBookId = typeOfBookId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Set<Book> getBook() {
        return listBook;
    }

    public void setBook(Set<Book> book) {
        this.listBook = book;
    }

    public void addBook(Book book) {
        this.listBook.add(book);
        book.getTypeOfBook().add(this);
    }

}
