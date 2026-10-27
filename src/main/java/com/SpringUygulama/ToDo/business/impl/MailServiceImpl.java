package com.SpringUygulama.ToDo.business.impl;

import com.SpringUygulama.ToDo.business.service.MailService;
import com.SpringUygulama.ToDo.business.service.JwtService;
import com.SpringUygulama.ToDo.dto.request.UserRequestDTO;
import com.SpringUygulama.ToDo.entity.User;
import com.SpringUygulama.ToDo.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {
    private JavaMailSender mailSender;
    private JwtService jwtService;
    private final UserRepository userRepository;

    public MailServiceImpl(JavaMailSender mailSender, JwtService jwtService, UserRepository userRepository) {
        this.mailSender = mailSender;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    public String sendMail(String setTo,String setText,String setSubject) {
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom("noreply@deneme.com");
        message.setTo(setTo);
        message.setText(setText);
        message.setSubject(setSubject);
        mailSender.send(message);
        return "Mail gönderildi";
    }
    @Override
    public void sendVerificationEmail(UserRequestDTO userRequestDTO) {
        String token = jwtService.generateMailToken(userRequestDTO.getEmail());
        String link = "http://localhost:8080/api/mail/verify?token=" + token;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(userRequestDTO.getEmail());
        message.setSubject("E-posta Doğrulama");
        message.setText("Lütfen hesabınızı doğrulamak için tıklayın: " + link);

        mailSender.send(message);
    }

    @Override
    public ResponseEntity<String> verifyUser(String token){
           String email = jwtService.extractEmail(token);
           User user = userRepository.findByEmail(email);
           if (user != null) {
               user.setVerified(true);
               userRepository.save(user);
               return ResponseEntity.ok("Hesabınız doğrulandı!");
           } else {
               return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Geçersiz token.");
           }
    }
}
