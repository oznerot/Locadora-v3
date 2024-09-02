package br.ufscar.dc.dsw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.ufscar.dc.dsw.domain.Rental;
import br.ufscar.dc.dsw.service.spec.IRentalService;
import jakarta.validation.Valid;

@RestController
public class RentalREST
{
    @Autowired
    private IRentalService service;

    //READ: Retorna lista de locacoes GET http://localhost:8080/locacoes
    @GetMapping(path = "/locacoes")
    public ResponseEntity<List<Rental>> list()
    {
        List<Rental> list = service.findAll();
        if(list.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(list);
    }

    //READ: Retorna uma locacao GET http://localhost:8080/locadoras/{id}
    @GetMapping(path = "/locacoes/{id}")
    public ResponseEntity<Rental> read(@PathVariable("id") Long id)
    {
        Rental rental = service.findById(id);
        if (rental == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(rental);
    }
    //READ: Retorna lista de locacoes por cliente  GET http://localhost:8080/locacoes/clientes/{id}
    @GetMapping(path = "/locacoes/clientes/{id}")
    public ResponseEntity<List<Rental>> listClientRentals(@PathVariable("id") Long id)
    {
        List<Rental> list = service.findAllByClient(id);
        System.out.println("Tamanho da Lista: " + list.size());
        
        if(list.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(list);
    }
    //READ: Retorna lista de locacoes por locadora GET http://localhost:8080/locacoes/locadoras/{id}
    @GetMapping(path = "/locacoes/locadoras/{id}") 
    public ResponseEntity<List<Rental>> listCompanyRentals(@PathVariable("id") Long id)
    {
        List<Rental> rentals = service.findAllByCompany(id);
        if(rentals.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(rentals);
    }
}