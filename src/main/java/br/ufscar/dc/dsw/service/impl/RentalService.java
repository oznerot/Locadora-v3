package br.ufscar.dc.dsw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.dao.IRentalDAO;
import br.ufscar.dc.dsw.domain.Rental;
import br.ufscar.dc.dsw.service.spec.IRentalService;

@Service
@Transactional(readOnly = false)
public class RentalService implements IRentalService {

	@Autowired
	IRentalDAO dao;
	
	public void save(Rental rental) {
		dao.save(rental);
	}
	
	public void delete(Long id) {
		dao.deleteById(id);
	}
	
	@Transactional(readOnly = true)
    public List<Rental> findRentalsByClientAndCompanyAndDate(Long clientId, Long companyId, String date) {
        return dao.findRentalsByClientAndCompanyAndDate(clientId.longValue(), companyId.longValue(), date);
    }


	@Transactional(readOnly = true)
	public Rental findById(Long id) {
		return dao.findById(id.longValue());
	}

	@Transactional(readOnly = true)
	public List<Rental> findAll()
	{
		return dao.findAll();
	}

	@Transactional(readOnly = true)
	public List<Rental> findAllByClient(Long id) {
		return dao.findAllByClient(id);
	}

	@Transactional(readOnly = true)
	public List<Rental> findAllByCompany(Long id) {
		return dao.findAllByCompany(id);
	}

}