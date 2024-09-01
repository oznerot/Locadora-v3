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

    //nao testei
    @GetMapping(path = "/locacoes/{id}")
    public ResponseEntity<Rental> read(@PathVariable("id") Long id){
        Rental rental = service.findById(id);
        if (rental == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(rental);
    }

    //nao testei
    //locacoes por locadora
    @GetMapping(path = "/locacoes/locadora/{id}") 
    public ResponseEntity<List<Rental>> readByCompany(@PathVariable("id") Long id){
        List<Rental> rentals = service.findAllByCompany(id);
        if (rentals.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(rentals);
    }
}