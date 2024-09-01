package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.Rental;

public interface IRentalService {

	Rental findById(Long id);

	List<Rental> findAll();

	List<Rental> findAllByClient(Long id);

	List<Rental> findAllByCompany(Long id);
	
    List<Rental> findRentalsByClientAndCompanyAndDate(Long clientId, Long companyId, String date);
	
	void save(Rental rental);
	
    void delete(Long id);
}