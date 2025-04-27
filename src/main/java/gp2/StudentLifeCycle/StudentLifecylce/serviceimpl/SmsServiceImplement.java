package gp2.StudentLifeCycle.StudentLifecylce.serviceimpl;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import gp2.StudentLifeCycle.StudentLifecylce.dtos.CandidateDto;
import gp2.StudentLifeCycle.StudentLifecylce.models.Candidate;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class SmsServiceImplement {

    private final String fromPhoneNumber = "+14782922391";

    public void sendSms(String toPhoneNumber, String messageBody) {
        if (!isValidPhoneNumber(toPhoneNumber)) {
            throw new IllegalArgumentException("Invalid phone number: " + toPhoneNumber);
        }
        Message message = Message.creator(
                new PhoneNumber(toPhoneNumber), // Recipient's phone number
                new PhoneNumber(fromPhoneNumber), // Your Twilio phone number
                messageBody // Message content
        ).create();

        System.out.println("Message sent with SID: " + message.getSid());
    }


    public void notifyCandidate(CandidateDto candidate) {
        String message = "Hello " + candidate.getName() + ", your status has been updated to STUDENT. Congratulations!";
        this.sendSms(candidate.getPhone(), message);
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        // Regex to validate E.164 format
        String regex = "^(?:\\+237|00237|237|0)?[0-9]{9}$";
        return Pattern.matches(regex, phoneNumber);
    }
}
