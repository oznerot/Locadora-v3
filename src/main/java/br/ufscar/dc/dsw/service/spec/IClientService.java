package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.Client;

public interface IClientService
{
    void save(Client client);
    void delete(Long id);
    Client findById(Long id);
    List<Client> findAll();
    Client findByEmail(String email);
}