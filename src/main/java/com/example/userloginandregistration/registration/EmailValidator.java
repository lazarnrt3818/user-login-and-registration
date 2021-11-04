package com.example.userloginandregistration.registration;

import com.example.userloginandregistration.appuser.UserRepository;
import org.springframework.stereotype.Service;

import java.util.function.Predicate;
import java.util.regex.Pattern;

@Service
public class EmailValidator implements Predicate<String> {
    private final String REGEX = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

    @Override
    public boolean test(String email) {
        //TODO:Regex to validate email;

        return Pattern.compile(REGEX)
                .matcher(email)
                .matches();
    }
}
