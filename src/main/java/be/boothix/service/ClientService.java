package be.boothix.service;

import be.boothix.domain.Client;
import be.boothix.dto.ClientDTO;
import be.boothix.repository.ClientRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client createClient(ClientDTO clientDTO) {
        Client client = new Client(null, clientDTO.getType(), clientDTO.getFirstName(), clientDTO.getLastName(), clientDTO.getDenomination(), clientDTO.getVatNumber(), clientDTO.getBceNumber(), clientDTO.getPhoneNumber(), clientDTO.getEmail(), clientDTO.getAddress());

        return clientRepository.save(client);
    }
}
