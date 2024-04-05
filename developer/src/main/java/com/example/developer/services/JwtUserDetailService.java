package com.example.developer.services;

import com.example.developer.models.Client;
import com.example.developer.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JwtUserDetailService implements UserDetailsService {

    public ClientRepository clientRepository;

    @Autowired
    public JwtUserDetailService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        Optional<Client> client = Optional.ofNullable(clientRepository.findClientByUsername(username));
        if (client.isPresent()) {
            Client client1 = client.get();
            return User.builder()
                    .username(client1.getUsername())
                    .password(client1.getPassword()).build();
        }
        else {
            throw new UsernameNotFoundException("Пользователь с таким именем не найден");
        }

    }
}
