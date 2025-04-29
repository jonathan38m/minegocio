package com.minegocio.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniquePersonValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniquePerson {
    String message() default "A person with this document type and number already exists";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
