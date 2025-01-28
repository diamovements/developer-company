package com.example.developer.services;

import com.example.developer.dto.ClientDTO;
import com.example.developer.models.Apartment;
import com.example.developer.models.Client;
import com.example.developer.models.Role;
import com.example.developer.repositories.ApartmentRepository;
import com.example.developer.repositories.ClientRepository;
import com.example.developer.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder pe;


    //вынести роль в перечисление
    private Role createRole() {
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }

    private ClientDTO mapToDTO(Client client) {
        ClientDTO clientDTO = new ClientDTO();
        String[] s = client.getName().split(" ");
        clientDTO.setEmail(client.getEmail());
        clientDTO.setFirstName(s[0]);
        clientDTO.setLastName(s[1]);
        return clientDTO;
    }

    //сделать optional
    public Client findByEmail(String email) {
        return clientRepository.findClientByEmail(email);
    }
    public void saveClient(ClientDTO clientDTO) {
        Client client = new Client();
        Role role = roleRepository.findRoleByName("ROLE_ADMIN");
        client.setPassword(pe.encode(clientDTO.getPassword()));
        client.setEmail(clientDTO.getEmail());
        client.setName(clientDTO.getFirstName() + " " + clientDTO.getLastName());
        if (role == null) {
            role = createRole();
        }
        client.setRoles(Arrays.asList(role));
        clientRepository.save(client);
    }
}
