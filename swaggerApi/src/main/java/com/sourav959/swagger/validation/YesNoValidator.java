package com.sourav959.swagger.validation;


import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class YesNoValidator implements ConstraintValidator<YesNo, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(StringUtils.isEmpty(value)){
            return false;
        }
        return value.equals("Y") || value.equals("N");
    }
}
