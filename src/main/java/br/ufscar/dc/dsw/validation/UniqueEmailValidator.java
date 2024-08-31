package br.ufscar.dc.dsw.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.ufscar.dc.dsw.dao.IUserDAO;
import br.ufscar.dc.dsw.domain.User;

@Component
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

	@Autowired
	private IUserDAO dao;

	@Override
	public boolean isValid(String Email, ConstraintValidatorContext context) {
		if (dao != null) {
			User user = dao.findByEmail(Email);
			return user == null;
		} else {
			return true;
		}
	}
}