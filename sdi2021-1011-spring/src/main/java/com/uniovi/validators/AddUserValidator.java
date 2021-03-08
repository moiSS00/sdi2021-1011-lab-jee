package com.uniovi.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.uniovi.entities.User;

@Component
public class AddUserValidator implements Validator{

	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		User user = (User) target;
		
		if (user.getName().length() < 5 || user.getName().length() > 24) {
			errors.rejectValue("name", "Error.addUser.name.value");
		}
		
		if (user.getLastName().length() < 5 || user.getLastName().length() > 24) {
			errors.rejectValue("lastName", "Error.addUser.lastName.value");
		}
		
		if (user.getDni().length() < 5 || user.getDni().length() > 24) {
			errors.rejectValue("dni", "Error.addUser.dni.value");
		}
		
		if (user.getPassword().length() < 5 || user.getPassword().length() > 24) {
			errors.rejectValue("password", "Error.addUser.password.value");
		}
		
	}

}
