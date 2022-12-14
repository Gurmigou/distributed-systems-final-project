package com.example.distributedsystemssoap.controller;

import com.example.distributedsystemssoap.repository.StudentRepository;
import io.spring.guides.gs_producing_web_service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class StudentEndpoint {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentEndpoint(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @PayloadRoot(localPart = "getStudentRequest")
    @ResponsePayload
    public GetStudentResponse getStudent(@RequestPayload GetStudentRequest request) {
        GetStudentResponse response = new GetStudentResponse();
        response.setStudent(studentRepository.findStudentById(request.getId()));
        return response;
    }

    @PayloadRoot(localPart = "createStudentRequest")
    @ResponsePayload
    public void createStudent(@RequestPayload CreateStudentRequest request) {
        studentRepository.createNewStudent(request.getStudent());
    }

    @PayloadRoot(localPart = "updateStudentRequest")
    @ResponsePayload
    public void updateStudent(@RequestPayload UpdateStudentRequest request) {
        studentRepository.updateStudent(request.getStudent());
    }

    @PayloadRoot(localPart = "deleteStudentRequest")
    @ResponsePayload
    public void deleteStudent(@RequestPayload DeleteStudentRequest request) {
        studentRepository.deleteStudent(request.getId());
    }
}
