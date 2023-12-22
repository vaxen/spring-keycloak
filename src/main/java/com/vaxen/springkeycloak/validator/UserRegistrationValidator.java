package com.vaxen.springkeycloak.validator;

import com.vaxen.springkeycloak.client.UserCreateRequest;

import java.util.function.Function;
import java.util.function.Predicate;

@FunctionalInterface
public interface UserRegistrationValidator extends Function<UserCreateRequest, UserRegistrationValidator.EmailValidationEnum> {

    static UserRegistrationValidator isEmailValid() {
        return holds(user -> {
            String email = user.getEmail();
            return email != null && email.matches(
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        }, EmailValidationEnum.EMAIL_NOT_VALID);
    }

    enum EmailValidationEnum {
        SUCCESS,
        EMAIL_NOT_VALID
    }

    static UserRegistrationValidator holds(Predicate<UserCreateRequest> p, EmailValidationEnum error) {
        return user -> p.test(user) ? EmailValidationEnum.SUCCESS : error;
    }
}