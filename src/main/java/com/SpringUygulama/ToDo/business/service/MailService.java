package com.SpringUygulama.ToDo.business.service;

import com.SpringUygulama.ToDo.dto.request.UserRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.resource.NoResourceFoundException;

public interface MailService {
    String sendMail(String setTo,String setText,String setSubject);
    void sendVerificationEmail(UserRequestDTO userRequestDTO);

    ResponseEntity<String> verifyUser(String token) throws NoResourceFoundException;
}
