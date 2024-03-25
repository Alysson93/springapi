package com.example.springapi.validation.constraintvalidation;

import java.util.List;

import com.example.springapi.validation.NotEmptyList;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@SuppressWarnings("rawtypes")
public class NotEmptyListValidator implements ConstraintValidator<NotEmptyList, List> {

    @Override
    public boolean isValid(List list, ConstraintValidatorContext context) {
        return list != null && !list.isEmpty();
    }

    @Override
    public void initialize(NotEmptyList constraintAnnotation) {
        
    }

}
