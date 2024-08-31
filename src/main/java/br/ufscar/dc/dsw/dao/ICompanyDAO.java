package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.ufscar.dc.dsw.domain.Company;

@SuppressWarnings("unchecked")
public interface ICompanyDAO extends CrudRepository<Company, Long>{
	Company findById(long id);

	List<Company> findAll();
	Company save(Company company);
	void deleteById(Long id);

	@Query("SELECT company FROM Company company WHERE company.cnpj = :cnpj")
	Company findByCNPJ(@Param ("cnpj") String cnpj);


	@Query("SELECT company FROM Company company WHERE company.email = :email")
	Company findByEmail(@Param ("email") String email);

	@Query("SELECT company FROM Company company WHERE company.city = :city")
    List<Company> findByCity(@Param("city") String city);

	@Query("SELECT DISTINCT company.city FROM Company company")
    List<String> findDistinctCities();

}