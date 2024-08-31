package br.ufscar.dc.dsw.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.dao.ICompanyDAO;
import br.ufscar.dc.dsw.dao.IRentalDAO;
import br.ufscar.dc.dsw.domain.Company;
import br.ufscar.dc.dsw.domain.Rental;
import br.ufscar.dc.dsw.service.spec.ICompanyService;

import java.util.Optional;


@Service
@Transactional(readOnly = false)
public class CompanyService implements ICompanyService {

	@Autowired
	ICompanyDAO companyDao;
	@Autowired
	IRentalDAO rentalDao;
	
	public void save(Company company) {
		companyDao.save(company);
	}
	
	public void delete(Long id) {
        Optional<Company> optionalCompany = companyDao.findById(id);
        if (optionalCompany.isPresent()) {
            Company company = optionalCompany.get();
            List<Rental> rentals = company.getRentals();

            for (Rental rental : rentals) {
                rental.setCompany(null); 
                rentalDao.save(rental); 
            }

            companyDao.delete(company);
		}
	}

	@Transactional(readOnly = true)
	public Company findById(Long id) {
		return companyDao.findById(id.longValue());
	}
	
	@Transactional(readOnly = true)
	public List<Company> findAll() {
		return companyDao.findAll();

	}    

	@Transactional(readOnly = true)
	public Company findByEmail(String email){
		return companyDao.findByEmail(email);
	}


	@Override
	public List<Company> findByCity(String city) {
    	return companyDao.findByCity(city);
	}

	@Override
    public List<String> findAvailableCities() {
        return companyDao.findDistinctCities();
    }
}