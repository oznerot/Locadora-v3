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

import br.ufscar.dc.dsw.domain.Client;
import br.ufscar.dc.dsw.domain.Rental;
import br.ufscar.dc.dsw.service.spec.IClientService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class ClientREST
{
    @Autowired
    private IClientService service;

    private boolean isJSONValid(String jsonInString) {
		try {
			return new ObjectMapper().readTree(jsonInString) != null;
		} catch (IOException e) {
			return false;
		}
	}

    @SuppressWarnings("unchecked")
    private void responseToJson(Client client, JSONObject json) {

		Object id = json.get("id");
		if (id != null) {
			if (id instanceof Integer) {
				client.setId(((Integer) id).longValue());
			} else {
				client.setId(((Long) id));
			}
		}
		
		if (json.containsKey("email")) {
			client.setEmail((String) json.get("email"));
		}
		
		if (json.containsKey("name")) {
			client.setName((String) json.get("name"));
		}
		
		if (json.containsKey("password")) {
			client.setPassword((String) json.get("password"));
		}
		
		if (json.containsKey("role")) {
			client.setRole((String) json.get("role"));
		}
		
		if (json.containsKey("cpf")) {
			client.setCpf((String) json.get("cpf"));
		}
		
		if (json.containsKey("phone")) {
			client.setPhone((String) json.get("phone"));
		}
		
		if (json.containsKey("gender")) {
			client.setGender((String) json.get("gender"));
		}
		
		if (json.containsKey("birthday")) {
			client.setBirthday((String) json.get("birthday"));
		}
		
		if (json.containsKey("rentals")) {
			// Verifica se o valor associado à chave é realmente uma lista
			Object rentals = json.get("rentals");
			if (rentals instanceof List) {
				client.setRentals((List<Rental>) rentals);
			}
		}
	}
    
    @GetMapping(path = "/clientes")
    public ResponseEntity<List<Client>> list()
    {
        List<Client> list = service.findAll();
        if(list.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(list);
    }
    
    @PostMapping(path = "/clientes")
    @ResponseBody
    public ResponseEntity<Client> insert(@Valid @RequestBody Client client, BindingResult result)
    {
        if(result.hasErrors())
        {
            return ResponseEntity.badRequest().body(null);
        }

        service.save(client);
        return ResponseEntity.ok(client);
    }

    // não testei
    //retorna cliente por id
    @GetMapping(path = "/clientes/{id}")
    public ResponseEntity<Client> read(@PathVariable("id") Long id){
        Client client = service.findById(id);
        if (client == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(client);
    }
    
    @PutMapping(path = "/clientes/{id}")
	public ResponseEntity<Client> atualiza(@PathVariable("id") long id, @RequestBody JSONObject json) {
		try {
			if (isJSONValid(json.toString())) {
				Client cliente = service.findById(id);
				if (cliente == null) {
					return ResponseEntity.notFound().build();
				} else {
					responseToJson(cliente, json);
					service.save(cliente);
					return ResponseEntity.ok(cliente);
				}
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
    }
}