package br.ufscar.dc.dsw;

@RestController
public class ClientREST
{
    @Autowired
    private IClientService service;

    @PostMapping("/clients")
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
}