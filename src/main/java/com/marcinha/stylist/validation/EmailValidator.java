package com.marcinha.stylist.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

    private Pattern pattern;
    private Matcher theMatcher;
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
//    private final static String EMAIL_PATTERN ="\b[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,4}\b";

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {

        pattern = Pattern.compile(EMAIL_PATTERN);

        if (email == null) {
            return false;
        }
        theMatcher = pattern.matcher(email);
        return theMatcher.matches();


    }
}
