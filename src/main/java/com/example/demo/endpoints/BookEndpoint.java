package com.example.demo.endpoints;

import com.example.demo.repository.BookRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.xml.school.*;
import com.example.demo.xml.school.book.GetAllBooksRequest;
import com.example.demo.xml.school.book.GetAllBooksResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class BookEndpoint {

    private static final String NAMESPACE_URI = "http://www.demo.example.com/xml/school/book";

    private BookRepository repository;

    @Autowired
    public BookEndpoint(BookRepository repository){
        this.repository = repository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetAllBooksRequest")
    @ResponsePayload
    public GetAllBooksResponse getAllBooksResponse (@RequestPayload GetAllBooksRequest request){
        GetAllBooksResponse response = new GetAllBooksResponse();
        response.getBook().addAll(repository.listAll());
        return response;
    }

}
