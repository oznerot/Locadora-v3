package br.ufscar.dc.dsw.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.dao.IClientDAO;
import br.ufscar.dc.dsw.dao.IRentalDAO;
import br.ufscar.dc.dsw.domain.Client;
import br.ufscar.dc.dsw.domain.Rental;
import br.ufscar.dc.dsw.service.spec.IClientService;

import java.util.Optional;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
@Transactional(readOnly = false)
public class ClientService implements IClientService {

	@Autowired
	IClientDAO dao;

	@Autowired
	IRentalDAO rentalDao;

	@PersistenceContext
    private EntityManager entityManager;

	public void save(Client client) {
		dao.save(client);
	}

	public void delete(Long id) {
		Optional<Client> optionalClient = dao.findById(id);
		if (optionalClient.isPresent()) {
			Client client = optionalClient.get();
			List<Rental> rentals = client.getRentals();
			for (Rental rental : rentals) {
				rental.setClient(null); 
				rentalDao.save(rental);
			}
			dao.delete(client);
		}
	}

	@Transactional(readOnly = true)
	public Client findById(Long id) {
		return dao.findById(id.longValue());
	}
	
	@Transactional(readOnly = true)
	public List<Client> findAll() {
		return dao.findAll();
	}    

	@Transactional(readOnly = true)
	public Client findByEmail(String email){
		return dao.findByEmail(email);
	}
}