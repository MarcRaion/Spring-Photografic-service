package com.marcinha.stylist.validation;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {


    private String firstFieldName;
    private String secondFieldName;
    private String message;

    @Override
    public void initialize(FieldMatch constraintAnnotation) {
        firstFieldName = constraintAnnotation.first();
        secondFieldName = constraintAnnotation.second();
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {

        boolean valid = true;
        try{

              Object firstObj = new BeanWrapperImpl(o).getPropertyValue(firstFieldName);
              Object secondObj = new BeanWrapperImpl(o).getPropertyValue(secondFieldName);

              valid = firstObj == null && secondObj == null || firstObj != null && secondObj.equals(firstObj);
        }
        catch (Exception ignore){

        }

        if(!valid){
            constraintValidatorContext.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(firstFieldName)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
        }
        return valid;
    }
}
