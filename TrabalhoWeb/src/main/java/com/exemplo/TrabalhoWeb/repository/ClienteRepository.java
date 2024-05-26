package com.exemplo.TrabalhoWeb.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.exemplo.TrabalhoWeb.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    Optional<Cliente> findByNomeCliente(String nomeCliente);

    List<Cliente> findByCpfCliente(String cpfCliente);

    Cliente findByIdCliente(Long idCliente);

    @Query("SELECT c FROM Cliente c WHERE c.nomeCliente LIKE %?1%")
    List<Cliente> findByPartialNomeCliente(String partialNomeCliente);
}
