package hiruni.project.medicinefinderbackend.service;

import hiruni.project.medicinefinderbackend.entity.Email;
import jakarta.mail.MessagingException;
import org.thymeleaf.context.Context;


// Interface
public interface EmailService {

    String sendSimpleMail(Email email);
    void sendHtmlMail(String to, String subject, String templateName, Context context) throws MessagingException;
}
