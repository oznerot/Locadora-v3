package br.ufscar.dc.dsw.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.ufscar.dc.dsw.dao.ICompanyDAO;
import br.ufscar.dc.dsw.domain.Company;

@Component
public class UniqueCnpjValidator implements ConstraintValidator<UniqueCnpj, String> {

	@Autowired
	private ICompanyDAO dao;

	@Override
	public boolean isValid(String CNPJ, ConstraintValidatorContext context) {
		if (dao != null) {
			Company company = dao.findByCNPJ(CNPJ);
			return company == null;
		} else {
            // Não necessidade de validação
			// Durante a execução da classe LivrariaMvcApplication
			// não há injeção de dependência. 
			return true;
		}
	}
}