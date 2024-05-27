package com.lotto.app.Lotto_Web_App.service;

import org.springframework.stereotype.Service;



@Service
public class EmailService {

//    @Autowired
//    private JavaMailSender mailSender;
//
//    @Value("${spring.mail.from}")
//    private String fromEmail;
//
//    @Value("${spring.mail.from.name}")
//    private String fromName;
//
//    public void sendEmail(String to, String subject, String text) {
//        MimeMessage message = mailSender.createMimeMessage();
//        try {
//            MimeMessageHelper helper = new MimeMessageHelper(message, true);
//            helper.setFrom(fromName + " <" + fromEmail + ">");
//            helper.setTo(to);
//            helper.setSubject(subject);
//            helper.setText(text, true);
//
//            mailSender.send(message);
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//    }

    // Email Testing

//    public void sendTestEmail() {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom(fromEmail);
//        message.setTo("asammiid3@gmail.com");
//        message.setSubject("Test Email");
//        message.setText("This is a test email sent from Spring Boot LottoWebApp.");
//        mailSender.send(message);
//    }
}
