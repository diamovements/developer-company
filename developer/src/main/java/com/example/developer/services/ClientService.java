package com.example.developer.services;

import com.example.developer.models.Client;
import com.example.developer.repositories.ClientRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ClientService implements UserDetailsService {

    @Autowired
    public ClientService(EntityManager em, ClientRepository clientRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.em = em;
        this.clientRepository = clientRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PersistenceContext
    private EntityManager em;
    private final ClientRepository clientRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client client = clientRepository.findClientByUsername(username);
        if (client == null) {
            throw new UsernameNotFoundException("No such client");
        }
        return User.builder().username(client.getUsername())
                .password(client.getPassword()).roles("USER").build();
    }
    public boolean save(Client client) {
        Client clientFromDB = clientRepository.findClientByUsername(client.getUsername());
        if (clientFromDB != null) {
            return false;
        }
        client.setPassword(bCryptPasswordEncoder.encode(client.getPassword()));
        clientRepository.save(client);
        return true;
    }
}
