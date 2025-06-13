package be.boothix.controller;

import be.boothix.domain.Client;
import be.boothix.dto.ClientDTO;
import be.boothix.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", exposedHeaders = "Location")
@RestController
@RequestMapping(value = "/api/client")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<ClientDTO> create(@RequestBody ClientDTO clientDTO) {
        Client client = this.clientService.createClient(clientDTO);

        ClientDTO createdClientDTO = new ClientDTO(client);

        return ResponseEntity.ok(createdClientDTO);
    }

    @PutMapping
    public ResponseEntity<ClientDTO> update(@RequestBody ClientDTO clientDTO) {
        Client client = this.clientService.updateClient(clientDTO);

        ClientDTO createdClientDTO = new ClientDTO(client);

        return ResponseEntity.ok(createdClientDTO);
    }

    @GetMapping
    public List<ClientDTO> getAll() { return this.clientService.getAllClients(); }

    @GetMapping("/{id}")
    public ClientDTO getById(@PathVariable Long id) {
        return this.clientService.getClientById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        this.clientService.deleteClient(id);
        return ResponseEntity.ok().build();
    }
}
