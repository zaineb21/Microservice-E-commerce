package com.example.livraisonms.services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsService {
   //error @Value("${twilio.accountSid}")
    private String accountSid;

  //error  @Value("${twilio.authToken}")
    private String authToken;

    public void sendSms(String to, String message) {
        Twilio.init(accountSid, authToken);
        Message.creator(new PhoneNumber(to), new PhoneNumber("+21650403261"), message).create();
    }
}
