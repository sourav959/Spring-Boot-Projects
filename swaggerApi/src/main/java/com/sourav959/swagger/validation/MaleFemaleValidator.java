package com.sourav959.swagger.validation;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MaleFemaleValidator implements ConstraintValidator<MaleFemale, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringUtils.isEmpty(value)) {
            return false;
        }
        return value.equals("m") || value.equals("f");
    }
}
