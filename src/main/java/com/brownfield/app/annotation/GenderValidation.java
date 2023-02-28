package com.brownfield.app.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = GenderValidator.class)
public @interface GenderValidation {

    public String message() default "Invalid value for gender";
    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default {};

}
