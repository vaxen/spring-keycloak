package com.vaxen.springkeycloak.validator;

import com.vaxen.springkeycloak.client.UserCreateRequest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserRegistrationValidatorTest {

    @Test
    public void whenEmailIsValid_thenValidationSucceeds() {
        UserCreateRequest user = new UserCreateRequest();
        user.setEmail("test@example.com");

        UserRegistrationValidator.EmailValidationEnum result = UserRegistrationValidator.isEmailValid()
                .apply(user);

        assertThat(result).isEqualTo(UserRegistrationValidator.EmailValidationEnum.SUCCESS);
    }

    @Test
    public void whenEmailIsInvalid_thenValidationFails() {
        UserCreateRequest user = new UserCreateRequest();
        user.setEmail("invalid_email");

        UserRegistrationValidator.EmailValidationEnum result = UserRegistrationValidator.isEmailValid()
                .apply(user);

        assertThat(result).isEqualTo(UserRegistrationValidator.EmailValidationEnum.EMAIL_NOT_VALID);
    }

    @Test
    public void whenEmailIsEmpty_thenValidationFails() {
        UserCreateRequest user = new UserCreateRequest();
        user.setEmail("");

        UserRegistrationValidator.EmailValidationEnum result = UserRegistrationValidator.isEmailValid()
                .apply(user);

        assertThat(result).isEqualTo(UserRegistrationValidator.EmailValidationEnum.EMAIL_NOT_VALID);
    }
}