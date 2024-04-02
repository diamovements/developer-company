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

    @PersistenceContext
    private EntityManager em;
    private ClientRepository clientRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client client = clientRepository.findClientByUsername(username);
        if (client == null) {
            throw new UsernameNotFoundException("No such client");
        }
        return User.builder().username(client.getUsername())
                .password(client.getPassword()).roles("USER").build();
    }

}
