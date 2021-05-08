package com.example.springwebdemo;

public class Book {
    private String title;
    private String ISBN;
    private Author author;

    public Book(String title, String ISBN) {
        this.title = title;
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void attachAuthor(Author author){
        this.author = author;
    }

    public String getInfo(){
        return "Książka: " + title +" o numerze ISBN " + ISBN + " autorstwa: " + author.getInfo();
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", author=" + author +
                '}';
    }
}
