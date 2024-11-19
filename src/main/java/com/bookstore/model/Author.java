
package com.bookstore.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authorId;
    private String fullName;
    private String story;
    @OneToMany(mappedBy="author")
    private List<Book> book;
    
    public void addBook(Book book){
        this.book.add(book);
    }

    public Author() {
    }

    public Author(Long authorId, String fullName, String story, List<Book> book) {
        this.authorId = authorId;
        this.fullName = fullName;
        this.story = story;
        this.book = book;
    }
     public Author(Long authorId, String fullName, String story) {
        this.authorId = authorId;
        this.fullName = fullName;
        this.story = story;
      
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public List<Book> getBook() {
        return book;
    }

    public void setBook(List<Book> book) {
        this.book = book;
    }
    
    
}
