package data_analytics_hub.functions;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {

    @org.junit.jupiter.api.Test
    public void testValidateUsername() {
        assertTrue(Validator.validateUsername("Username"));
        assertFalse(Validator.validateUsername(""));
    }

    @org.junit.jupiter.api.Test
    public void testValidateName() {
        assertTrue(Validator.validateName("Name"));
        assertFalse(Validator.validateName(""));
    }

    @org.junit.jupiter.api.Test
    public void testValidateEmail() {
        assertTrue(Validator.validateEmail("user@gmail.com"));
        assertFalse(Validator.validateEmail(""));
        assertFalse(Validator.validateEmail("email"));
    }

    @org.junit.jupiter.api.Test
    public void testValidatePassword() {
        assertTrue(Validator.validatePassword("password123"));
        assertFalse(Validator.validatePassword(""));
        assertFalse(Validator.validatePassword("123"));
    }

    @org.junit.jupiter.api.Test
    public void testValidateNumber() {
        assertTrue(Validator.validateNumber("123"));
        assertFalse(Validator.validateNumber(""));
        assertFalse(Validator.validateNumber("abc"));
    }

}