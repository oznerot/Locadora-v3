package br.ufscar.dc.dsw.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.ufscar.dc.dsw.dao.IRentalDAO;
import br.ufscar.dc.dsw.domain.Rental;

@Component
public class UniqueRentalValidator implements ConstraintValidator<UniqueRental, Rental> {

    @Autowired
    private IRentalDAO dao;

    @Override
    public boolean isValid(Rental rental, ConstraintValidatorContext context) {
        if(dao != null && rental != null) {
        	return dao.findRentalsByClientAndCompanyAndDate(rental.getCompany().getId(), rental.getClient().getId(), rental.getDate()).isEmpty();

        }
        
        return true;
    }
}