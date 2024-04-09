package com.example.developer.services;

import com.example.developer.dto.ClientDTO;
import com.example.developer.models.Client;
import com.example.developer.models.Role;
import com.example.developer.repositories.ClientRepository;
import com.example.developer.repositories.RoleRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder pe;

    @Autowired
    public ClientService(ClientRepository clientRepository, RoleRepository roleRepository, PasswordEncoder pe) {
        this.clientRepository = clientRepository;
        this.roleRepository = roleRepository;
        this.pe = pe;
    }

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
    public Client findByEmail(String email) {
        return clientRepository.findClientByEmail(email);
    }
    public List<ClientDTO> findAll() {
        List<Client> list = clientRepository.findAll();
        return list.stream().map(this::mapToDTO).collect(Collectors.toList());
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
