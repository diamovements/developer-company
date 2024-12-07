package com.example.developer.repositories;

import com.example.developer.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    Client findClientByEmail(String email);
    Optional<Client> findByEmail(String email);
}
