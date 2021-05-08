package com.example.springwebdemo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class Service {

    List<Book> bookList = new ArrayList<>();
    List<Author> authorList = new ArrayList<>();
    Map<Author, List<Book>> bookInfo = new HashMap<>();

    @GetMapping("/addBook")
    @ResponseBody
    void addBook(@RequestParam() String title, @RequestParam() String ISBN) {
        bookList.add(new Book(title, ISBN));
        for (Book element : bookList) {
            System.out.println(element);
        }
    }

    @GetMapping("/addAuthor")
    @ResponseBody
    void addAuthor(@RequestParam() String firstName, @RequestParam() String lastName) {
        authorList.add(new Author(firstName, lastName));
        for (Author element : authorList) {
            System.out.println(element);
        }
    }

    @GetMapping("/attachBook")
    @ResponseBody
    void attachBook(@RequestParam() String firstName, @RequestParam() String lastName, @RequestParam() String title) {
        Optional<Author> author = authorList.stream().filter(a -> a.getFirstName().equals(firstName) && a.getLastName().equals(lastName)).findFirst();
        Optional<Book> book = bookList.stream().filter(b -> b.getTitle().equals(title)).findFirst();
        if (author.isPresent() && book.isPresent()) {
            book.get().setAuthor(author.get());
            List<Book> books = bookInfo.get(author.get());
            if (books == null) {
                books = new ArrayList<>();
            }
            books.add(book.get());
            bookInfo.put(author.get(), books);
        }
    }

    @GetMapping("/bookInfo")
    @ResponseBody
    void bookInfo() {
        bookInfo.forEach((key, value) -> System.out.println(key + " " + value));
    }
}
