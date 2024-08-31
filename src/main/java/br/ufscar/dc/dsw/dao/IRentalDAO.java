package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;


import br.ufscar.dc.dsw.domain.Rental;
import br.ufscar.dc.dsw.domain.User;

@SuppressWarnings("unchecked")
public interface IRentalDAO extends CrudRepository<Rental, Long>{

	Rental findById(long id);

	@Query("SELECT rental FROM Rental rental WHERE rental.client.id = ?1")
    List<Rental> findAllByClient(Long clientId);

    @Query("SELECT rental FROM Rental rental WHERE rental.company.id = ?1")
    List<Rental> findAllByCompany(Long companyId);
	
	Rental save(Rental rental);
	
	void deleteById(Long id);

	@Query("SELECT rental FROM Rental rental WHERE (rental.company.id = ?1 OR rental.client.id = ?2) AND (rental.date = ?3)")
	List<Rental> findRentalsByClientAndCompanyAndDate(Long company, Long client, String date);
}