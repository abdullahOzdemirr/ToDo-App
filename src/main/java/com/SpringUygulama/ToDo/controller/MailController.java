package com.SpringUygulama.ToDo.controller;

import com.SpringUygulama.ToDo.business.service.MailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestController
@RequestMapping("/api/mail")
public class MailController {
    private final MailService mailService;

    public MailController(MailService mailService) {
        this.mailService = mailService;
    }
    @GetMapping("/normal")
    public ResponseEntity<String> sendNormalMail(@RequestParam String  setTo,@RequestParam String setText,@RequestParam String setSubject){
        return ResponseEntity.ok(mailService.sendMail(setTo,setText,setSubject));
    }
    @GetMapping("/verify")
    public ResponseEntity<String> verifyUser(@RequestParam String token) throws NoResourceFoundException {
        return mailService.verifyUser(token);
    }
}
