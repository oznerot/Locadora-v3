package br.ufscar.dc.dsw.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.ufscar.dc.dsw.dao.IClientDAO;
import br.ufscar.dc.dsw.domain.Client;

@Component
public class UniqueCpfValidator implements ConstraintValidator<UniqueCpf, String> {

	@Autowired
	private IClientDAO dao;

	@Override
	public boolean isValid(String cpf, ConstraintValidatorContext context) {
		if (dao != null)
        {
			Client client = dao.findByCpf(cpf);
			return client == null;
		}
        else
        {
			return true;
		}
	}
}