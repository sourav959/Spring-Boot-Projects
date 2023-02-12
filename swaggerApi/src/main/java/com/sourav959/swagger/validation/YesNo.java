package com.sourav959.swagger.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * YesNo 'Y'/'N' Switch Validation Annotation.
 *
 * @author sourav959
 */
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = YesNoValidator.class)
public @interface YesNo {

    String message() default "Invalid parameter, it should be 'Y' or 'N'.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
