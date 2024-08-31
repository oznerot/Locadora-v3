package br.ufscar.dc.dsw.service.spec;
import java.util.List;
import br.ufscar.dc.dsw.domain.Company;

public interface ICompanyService {
    void save(Company company);
    void delete(Long id);
	Company findById(Long id);
	List<Company> findAll();
    Company findByEmail(String email);
    List<Company> findByCity(String city);
    List<String> findAvailableCities();
}