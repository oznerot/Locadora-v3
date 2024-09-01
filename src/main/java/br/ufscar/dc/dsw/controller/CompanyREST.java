package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.util.List;

import org.json.simple.JSONObject;
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

import com.fasterxml.jackson.databind.ObjectMapper;

import br.ufscar.dc.dsw.domain.Company;
import br.ufscar.dc.dsw.domain.Rental;
import br.ufscar.dc.dsw.service.spec.ICompanyService;
import jakarta.validation.Valid;

@RestController
public class CompanyREST
{
    @Autowired
    private ICompanyService service;

    private boolean isJSONValid(String jsonInString) {
		try {
			return new ObjectMapper().readTree(jsonInString) != null;
		} catch (IOException e) {
			return false;
		}
	}

    @SuppressWarnings("unchecked")
    private void responseToJson(Company company, JSONObject json) {

		Object id = json.get("id");
		if (id != null) {
			if (id instanceof Integer) {
				company.setId(((Integer) id).longValue());
			} else {
				company.setId(((Long) id));
			}
		}
		
		if (json.containsKey("email")) {
			company.setEmail((String) json.get("email"));
		}
		
		if (json.containsKey("name")) {
			company.setName((String) json.get("name"));
		}
		
		if (json.containsKey("password")) {
			company.setPassword((String) json.get("password"));
		}
		
		if (json.containsKey("role")) {
			company.setRole((String) json.get("role"));
		}
		
		if (json.containsKey("cnpj")) {
			company.setCnpj((String) json.get("cpf"));
		}
		
		if (json.containsKey("phone")) {
			company.setCity((String) json.get("city"));
		}
		
        if (json.containsKey("rentals")) {
			// Verifica se o valor associado à chave é realmente uma lista
			Object rentals = json.get("rentals");
			if (rentals instanceof List) {
				company.setRentals((List<Rental>) rentals);
			}
		}
	}

    //listar locadoras
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

    //adicionar locadora
    @PostMapping(path = "/locadoras")
    @ResponseBody
    public ResponseEntity<Company> insert(@Valid @RequestBody Company company, BindingResult result)
    {
        if(result.hasErrors())
        {
            return ResponseEntity.badRequest().body(null);
        }

        service.save(company);
        return ResponseEntity.ok(company);
    }

    //nao testei
    //retorna locadora por id
    @GetMapping(path = "/locadoras/{id}")
    public ResponseEntity<Company> read(@PathVariable("id") Long id){
        Company company = service.findById(id);
        if (company == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(company);
    }

    @PutMapping(path = "/locadoras/{id}")
	public ResponseEntity<Company> atualiza(@PathVariable("id") long id, @RequestBody JSONObject json) {
		try {
			if (isJSONValid(json.toString())) {
				Company company = service.findById(id);
				if (company == null) {
					return ResponseEntity.notFound().build();
				} else {
					responseToJson(company, json);
					service.save(company);
					return ResponseEntity.ok(company);
				}
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
    }
}