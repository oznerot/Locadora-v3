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

import br.ufscar.dc.dsw.domain.Company;
import br.ufscar.dc.dsw.service.spec.ICompanyService;
import jakarta.validation.Valid;

@RestController
public class CompanyREST
{
    @Autowired
    private ICompanyService service;

    @GetMapping(path = "/locadoras")
    public ResponseEntity<List<Company>> list()
    {
        List<Company> list = service.findAll();
        if(list.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(list);
    }

    @DeleteMapping(path = "/locadoras/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") long id)
    {
        Company company = service.findById(id);

        if(company == null)
        {
            return ResponseEntity.notFound().build();
        }
        
        service.delete(id);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }
}