package com.example.demo.services;

import com.example.demo.models.Usuario;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class MailSenderService {
    @Value("${smtp.host}")
    String host;
    @Value("${smtp.port}")
    String port;
    @Value("${smtp.sender}")
    String senderEmail;
    @Value("${smtp.password}")
    String password;
    @Value("${smtp.billin}")
    String recipientEmail;
    @Value("${smtp.subject}")
    String subject;
    final String bsubject = "Nuevo Usuario Registrado";

    String logoResourceName = "img/aaldea.png";
    String cidLogo = "logo";

    @Autowired
    private JavaMailSender emailSender;

    public MailSenderService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendEmail(Usuario user)  throws MessagingException {
        //User
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        ClassPathResource logoImage = new ClassPathResource(logoResourceName);
        helper.setTo(recipientEmail);
        helper.setSubject(subject);
        helper.setText(buildBody(user.getNombre(), user.getApellidos(), user.getLugarDeResidencia(), user.getCorreoElectronico(), user.getFechaDeNacimiento(), user.getComentarios()), true);
        helper.addInline(cidLogo, logoImage);
        emailSender.send(message);
        log.info("Message : {}", buildBody(user.getNombre(), user.getApellidos(), user.getLugarDeResidencia(), user.getCorreoElectronico(), user.getFechaDeNacimiento(), user.getComentarios()));

        //Business
        MimeMessage bmessage = emailSender.createMimeMessage();
        MimeMessageHelper bhelper = new MimeMessageHelper(bmessage, true);
        bhelper.setTo(recipientEmail);
        bhelper.setSubject(bsubject);
        bhelper.setText(buildBbody(user.getNombre(), user.getApellidos(), user.getLugarDeResidencia(), user.getCorreoElectronico(), user.getFechaDeNacimiento(), user.getComentarios()), true);
        bhelper.addInline(cidLogo, logoImage);
        emailSender.send(bmessage);
        log.info("Message : {}", buildBody(user.getNombre(), user.getApellidos(), user.getLugarDeResidencia(), user.getCorreoElectronico(), user.getFechaDeNacimiento(), user.getComentarios()));
    }


    private String buildBody(String nombre,String apellidos, String lugar, String email, String fecha, String comentarios) {
        String html = "<html><body>" +
                "<p><img src=\"cid:" + cidLogo + "\" alt=\"Logo Corporativo\"></p>" +
                "<p>Bienvenido a AAldea!,</p>" +
                "<p>Sus datos han sido registrados en nuestro sistema:</p>" +
                "<p>Nombre: " + nombre + "</p>" +
                "<p>Apellidos: " + apellidos + "</p>" +
                "<p>Email: " + email + "</p>" +
                "<p>Lugar de residencia:"+ lugar +"</p>" +
                "<p>Fecha de nacimiento:"+ fecha +"</p>" +
                "<p>Comentarios:"+ comentarios +"</p>" +
                "<p>En breves nos pondremos en contacto, El equipo de AAldea.</p>" +
                "</body></html>";
        return html;
    }
    private String buildBbody(String nombre,String apellidos, String lugar, String email, String fecha, String comentarios) {
        String html = "<html><body>" +
                "<p><img src=\"cid:" + cidLogo + "\" alt=\"Logo Corporativo\"></p>" +
                "<p>Nuevo Registro!,</p>" +
                "<p>El siguiente usuario se añadió al sistema:</p>" +
                "<p>Nombre: " + nombre + "</p>" +
                "<p>Apellidos: " + apellidos + "</p>" +
                "<p>Email: " + email + "</p>" +
                "<p>Lugar de residencia:"+ lugar +"</p>" +
                "<p>Fecha de nacimiento:"+ fecha +"</p>" +
                "<p>Comentarios:"+ comentarios +"</p>" +
                "<p>Póngase en contacto con el cliente:" + nombre + apellidos + "a través del correo electrónico:"+ email + "</p>" +
                "</body></html>";
        return html;
    }



}
