package gp2.StudentLifeCycle.StudentLifecylce.serviceimpl;

import gp2.StudentLifeCycle.StudentLifecylce.dtos.CandidateDto;
import gp2.StudentLifeCycle.StudentLifecylce.models.Candidate;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImplement {
    @Autowired
    private JavaMailSender javaMailSender;

    private final String sender = "saintjeancrmvafc@gmail.com";

    // Method 1
    // To send a simple email
    public String sendSimpleMail(CandidateDto candidateDto)
    {

        // Try block to check for exceptions
        try {

            // Creating a simple mail message
            SimpleMailMessage mailMessage
                    = new SimpleMailMessage();

            // Setting up necessary details
            mailMessage.setFrom(sender);
            mailMessage.setTo(candidateDto.getEmail());
            mailMessage.setText("Hello, Dear Candidate\nWe are happy to inform you that you application as" +
                    "being a Candidate for Our Exam has been Validated\n\n  ");
            mailMessage.setSubject("Saint Jean Registration");

            // Sending the mail
            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully...";
        }

        // Catch block to handle the exceptions
        catch (Exception e) {
            return "Error while Sending Mail";
        }
    }

    public String sendSimpleMailforcandi(Candidate candidateDto)
    {

        // Try block to check for exceptions
        try {

            // Creating a simple mail message
            SimpleMailMessage mailMessage
                    = new SimpleMailMessage();

            // Setting up necessary details
            mailMessage.setFrom(sender);
            mailMessage.setTo(candidateDto.getEmail());
            mailMessage.setText("You are a Student");
            mailMessage.setSubject("Saint Jean Registration");

            // Sending the mail
            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully...";
        }

        // Catch block to handle the exceptions
        catch (Exception e) {
            return "Error while Sending Mail";
        }
    }
}
