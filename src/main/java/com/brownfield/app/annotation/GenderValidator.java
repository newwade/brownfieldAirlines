package com.brownfield.app.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class GenderValidator implements ConstraintValidator<GenderValidation,String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        List list = Arrays.asList(new String[]{"male", "female","other"});
        return list.contains(value);
    }
}
