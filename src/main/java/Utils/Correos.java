package Utils;

import ENTITY.Empleado;
import ENTITY.Usuario_ADM;
import Utils.ContraAutomatica;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class Correos {
    private static final String FROM_EMAIL = "jylgimnasio@gmail.com";
    private static final String PASSWORD = "ynrohhhcrmauiiei";

    private static final Properties PROPS = new Properties();

    static {
        PROPS.put("mail.smtp.host", "smtp.gmail.com");
        PROPS.put("mail.smtp.port", "587");
        PROPS.put("mail.smtp.auth", "true");
        PROPS.put("mail.smtp.starttls.enable", "true");
        PROPS.put("mail.smtp.ssl.protocols", "TLSv1.2");
    }

    private static final Session SESSION = Session.getInstance(PROPS, new Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(FROM_EMAIL, PASSWORD);
        }
    });

    public void enviarCorreo(Empleado empleado, Usuario_ADM usuarioAdm) {
        String saludo = empleado.getGenero().equals("M") ? "BIENVENIDO" : "BIENVENIDA";

        try {
            MimeMessage message = new MimeMessage(SESSION);
            message.setFrom(new InternetAddress(FROM_EMAIL));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(empleado.getEmail()));
            message.setSubject("¡SUSCRIPCION REALIZADA CON EXITO!");

            MimeMultipart multipart = new MimeMultipart();

            MimeBodyPart textPart = new MimeBodyPart();
            String contenidoMensaje = buildMensaje(empleado, usuarioAdm, saludo);
            textPart.setContent(contenidoMensaje, "text/html");
            multipart.addBodyPart(textPart);

            MimeBodyPart attachmentPart = new MimeBodyPart();
            String rutaDelArchivo = "C:\\Users\\juan1\\IdeaProjects\\DesktopGimnasio\\src\\main\\resources\\documentos\\saludo.pdf";
            attachmentPart.attachFile(new File(rutaDelArchivo));
            multipart.addBodyPart(attachmentPart);

            message.setContent(multipart);

            System.out.println("Enviando correo...");
            Transport.send(message);
            System.out.println("¡Correo enviado exitosamente!");
        } catch (MessagingException | IOException e) {
            System.out.println("Ocurrió un error al enviar el correo: " + e.getMessage());
        }
    }

    private String buildMensaje(Empleado empleado, Usuario_ADM usuarioAdm, String saludo) {
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("¡HOLA, ").append(empleado.getNombre()).append("! ¡").append(saludo).append(" A NUESTRO GIMNASIO!<br><br>")
                .append("Estamos encantados de tenerte como parte de nuestra comunidad. Esperamos que tu experiencia con nosotros sea gratificante y te ayude a alcanzar tus objetivos fitness.<br><br>")
                .append("A continuación, te proporcionamos la información de tu cuenta:<br><br>")
                .append("Usuario: ").append(usuarioAdm.getUsername()).append("<br>")
                .append("Contraseña: <strong>").append(ContraAutomatica.generarContraseña()).append("</strong><br><br>")
                .append("Por favor, no dudes en contactarnos si tienes alguna pregunta o necesitas ayuda. ¡Que tengas un gran día y un entrenamiento exitoso!<br><br>")
                .append("Atentamente,<br>")
                .append("Equipo de <strong><em>JYL Gimnasio</em></strong>");
        return mensaje.toString();
    }
}
