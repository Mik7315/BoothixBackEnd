package be.boothix.service;

import be.boothix.domain.Client;
import be.boothix.dto.ClientDTO;
import be.boothix.repository.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public Client updateClient(ClientDTO clientDTO) {
        if (clientDTO == null || clientDTO.getIdClient() == null) {
            throw new IllegalArgumentException("Client ID is required for update.");
        }

        Client existingClient = clientRepository.findById(clientDTO.getIdClient()).orElseThrow(() ->
                new EntityNotFoundException("Client not found with ID: " + clientDTO.getIdClient()));


        if (clientDTO.getEmail() != null) {
            Optional<Client> clientWithSameEmail = clientRepository.findByEmail(clientDTO.getEmail());
            if (clientWithSameEmail.isPresent() && !clientWithSameEmail.get().getIdClient().equals(clientDTO.getIdClient())) {
                throw new IllegalStateException("Email already used by another client.");
            }
        }
        if (clientDTO.getDenomination() != null) {
            Optional<Client> clientWithSameDenomination = clientRepository.findByDenomination(clientDTO.getDenomination());
            if (clientWithSameDenomination.isPresent() && !clientWithSameDenomination.get().getIdClient().equals(clientDTO.getIdClient())) {
                throw new IllegalStateException("Denomination already used by another client.");
            }
        }
        if (clientDTO.getVatNumber() != null) {
            Optional<Client> clientWithSameVatNumber = clientRepository.findByVatNumber(clientDTO.getVatNumber());
            if (clientWithSameVatNumber.isPresent() && !clientWithSameVatNumber.get().getIdClient().equals(clientDTO.getIdClient())) {
                throw new IllegalStateException("Vat number already used by another client.");
            }
        }
        if (clientDTO.getBceNumber() != null) {
            Optional<Client> clientWithSameBceNumber = clientRepository.findByBceNumber(clientDTO.getBceNumber());
            if (clientWithSameBceNumber.isPresent() && !clientWithSameBceNumber.get().getIdClient().equals(clientDTO.getIdClient())) {
                throw new IllegalStateException("Bce number already used by another client.");
            }
        }

        existingClient.setType(clientDTO.getType());
        existingClient.setFirstName(clientDTO.getFirstName());
        existingClient.setLastName(clientDTO.getLastName());
        existingClient.setDenomination(clientDTO.getDenomination());
        existingClient.setVatNumber(clientDTO.getVatNumber());
        existingClient.setBceNumber(clientDTO.getBceNumber());
        existingClient.setPhoneNumber(clientDTO.getPhoneNumber());
        existingClient.setEmail(clientDTO.getEmail());

        if (clientDTO.getAddress() != null) { existingClient.setAddress(clientDTO.getAddress()); }

        return clientRepository.save(existingClient);
    }

    public List<ClientDTO> getAllClients() {
        return this.clientRepository.findAll().stream().sorted(Comparator.comparing((Client::getIdClient))).map(ClientDTO::new).collect(Collectors.toList());
    }

    public ClientDTO getClientById(Long id) {
        return this.clientRepository.findById(id).stream().map(ClientDTO::new).findFirst().get();
    }
}
