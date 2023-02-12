package com.sourav959.swagger.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * MaleFemale 'm'/'f' Switch Validation Annotation.
 *
 * @author sourav959
 */
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MaleFemaleValidator.class)
public @interface MaleFemale {

    String message() default "Invalid parameter, it should be 'm' or 'f'.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
