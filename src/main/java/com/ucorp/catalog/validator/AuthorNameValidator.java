package com.ucorp.catalog.validator;

import org.springframework.stereotype.Component;

import com.ucorp.catalog.validator.annotaion.ValidAuthorName;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class AuthorNameValidator implements ConstraintValidator<ValidAuthorName,String>{

	@Override
	public boolean isValid(String authorName, ConstraintValidatorContext context) {
		// TODO Auto-generated method stu
		return !authorName.equalsIgnoreCase("Umam");
	}
	

}
