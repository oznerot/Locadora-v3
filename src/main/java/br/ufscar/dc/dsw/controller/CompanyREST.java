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

    //CREATE: Cria uma nova locadora  POST http://localhost:8080/locadoras
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

    //READ: Retorna lista de locadoras  GET http://localhost:8080/locadoras
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
    //READ: Retorna lista de locadoras por cidade  GET http://localhost:8080/locadoras/{city}
    @GetMapping(path = "/locadoras/cidades/{city}")
    public ResponseEntity<List<Company>> listByCity(@PathVariable("city") String city)
    {
        List<Company> list = service.findByCity(city);
        if(list.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(list);
    }

    //READ: Retorna uma locadora  GET http://localhost:8080/locadoras/{id}
    @GetMapping(path = "/locadoras/{id}")
    public ResponseEntity<Company> read(@PathVariable("id") Long id)
    {
        Company company = service.findById(id);
        if(company == null)
        {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(company);
    }

    //UPDATE: Atualiza uma locadora     PUT http://localhost:8080/locadoras/{id}
    @PutMapping(path = "/locadoras/{id}")
	public ResponseEntity<Company> atualiza(@PathVariable("id") long id,
                                            @RequestBody JSONObject json)
    {
		try
        {
			if(isJSONValid(json.toString()))
            {
                System.out.println("JASON VALIDO");
				Company company = service.findById(id);
				if(company == null)
                {
					return ResponseEntity.notFound().build();
				}
                else
                {
                    System.out.println("LOCADORA NO ELSE");
					responseToJson(company, json);
					service.save(company);
					return ResponseEntity.ok(company);
				}
			}
            else
            {
				return ResponseEntity.badRequest().body(null);
			}
		}
        catch(Exception e)
        {
            System.out.println("JASON LANCOU EXCECAO");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
    }

    //DELETE: Remove uma locadora   DELETE http://localhost:8080/locadoras/{id}
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

    //Helper methods
    private boolean isJSONValid(String jsonInString)
    {
		try
        {
			return new ObjectMapper().readTree(jsonInString) != null;
		}
        catch(IOException e)
        {
			return false;
		}
	}

    @SuppressWarnings("unchecked")
    private void responseToJson(Company company, JSONObject json)
    {		
		if(json.containsKey("email"))
        {
			company.setEmail(json.get("email").toString());
		}
		
		if(json.containsKey("name"))
        {
			company.setName(json.get("name").toString());
		}
		
		if(json.containsKey("password"))
        {
			company.setPassword(json.get("password").toString());
		}
		
		if(json.containsKey("role"))
        {
			company.setRole(json.get("role").toString());
		}
		
		if(json.containsKey("cnpj"))
        {
			company.setCnpj(json.get("cnpj").toString());
		}
		
		if(json.containsKey("city"))
        {
			company.setCity(json.get("city").toString());
		}
		
        if(json.containsKey("rentals"))
        {
			// Verifica se o valor associado à chave é realmente uma lista
			Object rentals = json.get("rentals");
			if(rentals instanceof List)
            {
				company.setRentals((List<Rental>) rentals);
			}
		}
	}
}