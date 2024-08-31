package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.ufscar.dc.dsw.domain.Client;

@SuppressWarnings("unchecked")
public interface IClientDAO extends CrudRepository<Client, Long>{
	Client findById(long id);

	List<Client> findAll();

	Client save(Client client);

	void deleteById(Long id);

	@Query("SELECT client FROM Client client WHERE client.cpf = :cpf")
    public Client findByCpf(@Param("cpf") String cpf);

	@Query("SELECT client FROM Client client WHERE client.email = :email")
	Client findByEmail(@Param ("email") String email);

}