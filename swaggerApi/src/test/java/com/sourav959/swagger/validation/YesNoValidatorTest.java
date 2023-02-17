package com.sourav959.swagger.validation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("YesNo validator junit Test")
@ExtendWith(MockitoExtension.class)
public class YesNoValidatorTest {

    @InjectMocks
    private YesNoValidator yesNoValidator;

    @Test
    @DisplayName("isValid for null value Test")
    void isValidForNullValueTest(){
        assertFalse(yesNoValidator.isValid(null,null));
    }

    @Test
    @DisplayName("isValid for empty string Test")
    void isValidForEmptyStringTest(){
        assertFalse(yesNoValidator.isValid("",null));
    }

    @Test
    @DisplayName("isValid for any invalid value Test")
    void isValidForInvalidTest(){
        assertFalse(yesNoValidator.isValid("No",null));
    }

    @Test
    @DisplayName("isValid for Yes value Test")
    void isValidForMaleValueTest(){
        assertTrue(yesNoValidator.isValid("Y",null));
    }

    @Test
    @DisplayName("isValid for No value Test")
    void isValidForFemaleValueTest(){
        assertTrue(yesNoValidator.isValid("N",null));
    }

}
