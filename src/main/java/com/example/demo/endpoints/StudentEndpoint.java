package com.example.demo.endpoints;

import com.example.demo.repository.StudentRepository;
import com.example.demo.xml.school.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class StudentEndpoint {

    private static final String NAMESPACE_URI = "http://www.demo.example.com/xml/school";

    private StudentRepository repository;

    @Autowired
    public StudentEndpoint (StudentRepository repository){
        this.repository = repository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "StudentDetailsRequest")
    @ResponsePayload
    public StudentDetailsResponse getStudentDetailsResponse (@RequestPayload StudentDetailsRequest request){
        StudentDetailsResponse response = new StudentDetailsResponse();
        response.setStudent(repository.findStudent(request.getName()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CreateStudentRequest")
    @ResponsePayload
    public CreateStudentResponse createStudent (@RequestPayload CreateStudentRequest student){
        CreateStudentResponse response = new CreateStudentResponse();
        boolean success = repository.addNewStudent(student.getStudent());
        response.setSuccess(success);
        response.setMessage(success? "Created success" : "Create fail");
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetAllStudentsRequest")
    @ResponsePayload
    public GetAllStudentsResponse getAllStudentsResponse (@RequestPayload GetAllStudentsRequest request){
        GetAllStudentsResponse response = new GetAllStudentsResponse();
        response.getStudent().addAll(repository.listAll());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "UpdateStudentRequest")
    @ResponsePayload
    public UpdateStudentResponse updateStudentResponse (@RequestPayload UpdateStudentRequest request){
        UpdateStudentResponse response = new UpdateStudentResponse();
        boolean success = repository.updateStudent(request.getStudent());
        response.setSuccess(success);
        response.setStudent(request.getStudent());
        response.setMessage(success? "Update success" : "Update fail");
        return response;
    }
}
