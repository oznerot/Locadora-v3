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

// TODO:
//  CREATE: Cria um novo cliente        POST http://localhost:8080/clientes
//  READ:   Retorna lista de clientes   GET http://localhost:8080/clientes
//  READ:   Retorna um cliente          GET http://localhost:8080/clientes/{id}
//  UPDATE: Atualiza um cliente         PUT http://localhost:8080/clientes/{id}
//  DELETE: Remove um cliente           DELETE http://localhost:8080/clientes/{id}

@RestController
public class ClientREST
{
    @Autowired
    private IClientService service;

    //CREATE: Cria um novo cliente  POST http://localhost:8080/clientes
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

    //READ: Retorna lista de clientes   GET http://localhost:8080/clientes
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

    //READ: Retorna um cliente  GET http://localhost:8080/clientes/{id}
    @GetMapping(path = "/clientes/{id}")
    public ResponseEntity<Client> read(@PathVariable("id") Long id)
    {
        Client client = service.findById(id);
        if (client == null)
        {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(client);
    }

    //UPDATE: Atualiza um cliente   PUT http://localhost:8080/clientes/{id}
    @PutMapping(path = "/clientes/{id}")
	public ResponseEntity<Client> atualiza(@PathVariable("id") long id,
                                           @RequestBody JSONObject json)
    {
		try
        {
			if (isJSONValid(json.toString()))
            {
				Client client = service.findById(id);
				if (client == null)
                {
					return ResponseEntity.notFound().build();
				}
                else
                {
					responseToJson(client, json);
					service.save(client);
					return ResponseEntity.ok(client);
				}
			}
            else
            {
                System.out.println("JASON INVALIDO");

				return ResponseEntity.badRequest().body(null);
			}
		}
        catch(Exception e)
        {
            System.out.println("CAIU NA EXCECAO");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
    }

    //DELETE: Remove um cliente     DELETE http://localhost:8080/clientes/{id}
    @DeleteMapping(path = "/clientes/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") long id)
    {
        Client client = service.findById(id);

        if(client == null)
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
    private void responseToJson(Client client, JSONObject json)
    {	
		if(json.containsKey("email"))
        {
			client.setEmail(json.get("email").toString());
		}
	
		if(json.containsKey("name"))
        {
			client.setName(json.get("name").toString());
		}
		
		if(json.containsKey("password"))
        {
			client.setPassword(json.get("password").toString());
		}
	
		if(json.containsKey("role"))
        {
			client.setRole(json.get("role").toString());
		}
	
		if(json.containsKey("cpf"))
        {
			client.setCpf(json.get("cpf").toString());
		}
		
		if(json.containsKey("phone"))
        {
			client.setPhone(json.get("phone").toString());
		}
		
		if(json.containsKey("gender"))
        {
			client.setGender(json.get("gender").toString());
		}
	
		if(json.containsKey("birthday"))
        {
			client.setBirthday(json.get("birthday").toString());
		}

		if(json.containsKey("rentals"))
        {
			// Verifica se o valor associado à chave é realmente uma lista
			Object rentals = json.get("rentals");
			if(rentals instanceof List)
            {
				client.setRentals((List<Rental>) rentals);
			}
		}
	}
}