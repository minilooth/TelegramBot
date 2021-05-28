package by.minilooth.telegrambot.validation;

import by.minilooth.telegrambot.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;

public class Validator {

    private final static String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private final static String FIRSTNAME_REGEX = "^[A-Za-zА-Яа-я]+$"; 
    private final static String SURNAME_REGEX = "^[A-Za-zА-Яа-я]+$";
    private final static String PHONE_NUMBER_REGEX = "^[+]{1}[3]{1}[7]{1}[5]{1}[(]{1}[0-9]{2}[)]{1}[-\\s/0-9]{9}$";
    
    private final static Integer EMAIL_MIN_LENGTH = 10;
    private final static Integer EMAIL_MAX_LENGTH = 40; 
    private final static Integer FIRSTNAME_MIN_LENGTH = 2;
    private final static Integer FIRSTNAME_MAX_LENGTH = 40;
    private final static Integer SURNAME_MIN_LENGTH = 3;
    private final static Integer SURNAME_MAX_LENGTH = 40;

    public static void isEmailValid(String email) throws ValidationException {
        if (!email.matches(EMAIL_REGEX)) {
            throw new ValidationException("message.email.incorrectFormat");
        }
        if (email.length() < EMAIL_MIN_LENGTH || email.length() > EMAIL_MAX_LENGTH) {
            throw new ValidationException("message.email.incorrectLength");
        }
    }

    public static void isFirstnameValid(String firstname) throws ValidationException {
        if (!firstname.matches(FIRSTNAME_REGEX)) {
            throw new ValidationException("message.firstname.incorrectFormat");
        }
        if (firstname.length() < FIRSTNAME_MIN_LENGTH || firstname.length() > FIRSTNAME_MAX_LENGTH) {
            throw new ValidationException("message.firstname.incorrectLength");
        }
    }

    public static void isSurnameValid(String surname) throws ValidationException {
        if (!surname.matches(SURNAME_REGEX)) {
            throw new ValidationException("message.surname.incorrectFormat");
        }
        if (surname.length() < SURNAME_MIN_LENGTH || surname.length() > SURNAME_MAX_LENGTH) {
            throw new ValidationException("message.surname.incorrectLength");
        }
    }

    public static void isPhoneNumberValid(String phoneNumber) throws ValidationException {
        if (!phoneNumber.matches(PHONE_NUMBER_REGEX)) {
            throw new ValidationException("message.mobilePhone.incorrectFormat");
        }
    }

}
