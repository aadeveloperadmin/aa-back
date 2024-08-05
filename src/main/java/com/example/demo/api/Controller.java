package com.example.demo.api;

import com.example.demo.models.Usuario;
import com.example.demo.services.Drive;
import com.example.demo.services.MailSenderService;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@Slf4j
@RequestMapping("/api/usuarios")
public class Controller {

        private MailSenderService mailSenderService;
        private Drive driveService;

        public Controller(MailSenderService mailSenderService, Drive driveService) {
            this.mailSenderService = mailSenderService;
            this.driveService = driveService;
        }

        @PostMapping("/addUserAndEmail")
        public ResponseEntity<String> recibirUsuario(@RequestBody Usuario usuario) throws MessagingException {
            try {
                driveService.addUserToSheet(usuario,"18QSwqkyrTjlC9OtbQBj6SZgjr3gkr0okF7wjGAe3EnM","A2:F19");
                mailSenderService.sendEmail(usuario);
            } catch (Exception e) {
                return (ResponseEntity<String>) ResponseEntity.internalServerError();
            }
            log.info("Error al intentar guardar los datos");
            return ResponseEntity.ok("Correo enviado con éxito a " + usuario.getCorreoElectronico());
        }
}

