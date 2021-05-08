package com.example.springwebdemo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class Service {

    List<Book> bookList = new ArrayList<>();
    List<Author> authorList = new ArrayList<>();
    Map<Author,List<Book>> bookInfo= new HashMap<>();

    @GetMapping("/addBook")
    @ResponseBody
    void addBook(@RequestParam() String title, @RequestParam() String ISBN){
        bookList.add(new Book(title, ISBN));
        for( Object element : bookList){
            System.out.println( element);
        }
    }

    @GetMapping("/addAuthor")
    @ResponseBody
    void addAuthor(@RequestParam() String firstName, @RequestParam() String lastName){
        authorList.add(new Author(firstName, lastName));
        for( Object element : authorList){
            System.out.println( element);
        }
    }

    @GetMapping("/attachBook")
    @ResponseBody
    void attachBook(@RequestParam() String firstName, @RequestParam() String lastName, @RequestParam() String title){
        Optional<Author> author = authorList.stream().filter(a -> a.getFirstName()==firstName&&a.getLastName()==lastName).findFirst();
        Optional<Book> book = bookList.stream().filter(b -> b.getTitle()==title).findFirst();
        List<Book> books = bookInfo.get(author);
        if(author.isPresent()&&book.isPresent()) {
            if (books == null) {
                books = new ArrayList<>();
            }
            books.add(book.get());
            bookInfo.put(author.get(), books);
            bookInfo.get(0);
            bookInfo.get(1);
        }
    }
    @GetMapping("/bookInfo")
    @ResponseBody
    void bookInfo(){
        System.out.println("Cos");
//        for( Author key : bookInfo.keySet()){
//            System.out.println( key);
//            System.out.println( bookInfo.get( key));
//        }
        bookInfo.get(0);
        bookInfo.get(1);
    }
}
