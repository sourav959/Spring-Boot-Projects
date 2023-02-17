package com.sourav959.swagger.validation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("MaleFemale validator junit Test")
@ExtendWith(MockitoExtension.class)
public class MaleFemaleValidatorTest {

    @InjectMocks
    private MaleFemaleValidator maleFemaleValidator;

    @Test
    @DisplayName("isValid for null value Test")
    void isValidForNullValueTest(){
        assertFalse(maleFemaleValidator.isValid(null,null));
    }

    @Test
    @DisplayName("isValid for empty string Test")
    void isValidForEmptyStringTest(){
        assertFalse(maleFemaleValidator.isValid("",null));
    }

    @Test
    @DisplayName("isValid for any invalid value Test")
    void isValidForInvalidTest(){
        assertFalse(maleFemaleValidator.isValid("male",null));
    }

    @Test
    @DisplayName("isValid for male value Test")
    void isValidForMaleValueTest(){
        assertTrue(maleFemaleValidator.isValid("m",null));
    }

    @Test
    @DisplayName("isValid for female value Test")
    void isValidForFemaleValueTest(){
        assertTrue(maleFemaleValidator.isValid("f",null));
    }

}
