package com.example.springwebdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Service service;

    @Test
    public void shouldDoSth() throws Exception {
        //given
        thereIsABook("Ani ogniem, ani mieczem", "123");
        thereIsABook("Pan Twardokęski", "234");
        thereIsABook("Kaj Leziesz", "345");
        thereIsAuthor("Staszek", "Miśkeiwicz");

        //when
        assign("Staszek", "Miśkeiwicz", "Ani ogniem, ani mieczem");

        //then
        assertThat(thereIsBookAssignedToAutorh("Staszek", "Miśkeiwicz", "Ani ogniem, ani mieczem")).isTrue();
    }

    private boolean thereIsBookAssignedToAutorh(String firstName, String lastName, String title) {
        Optional<Author> author = service.authorList.stream()
                .filter(a -> a.getFirstName().equals(firstName) && a.getLastName().equals(lastName))
                .findFirst();
        Optional<Book> book = service.bookList.stream()
                .filter(b -> b.getTitle().equals(title))
                .findFirst();
        if (author.isPresent() && book.isPresent()) {
            List<Book> books = service.bookInfo.get(author.get());
            return books.contains(book.get());
        }
        return false;
    }

    private void assign(String firstName, String lastName, String title) throws Exception {
        mockMvc.perform(
                get("/attachBook")
                        .param("firstName", firstName)
                        .param("lastName", lastName)
                        .param("title", title))
                .andExpect(status().isOk()
                );
    }

    private void thereIsABook(String title, String isbn) throws Exception {
        mockMvc.perform(
                get("/addBook")
                        .param("title", title)
                        .param("ISBN", isbn))
                .andExpect(status().isOk()
                );
    }

    private void thereIsAuthor(String firstName, String lastName) throws Exception {
        mockMvc.perform(
                get("/addAuthor")
                        .param("firstName", firstName)
                        .param("lastName", lastName))
                .andExpect(status().isOk()
                );
    }

}
