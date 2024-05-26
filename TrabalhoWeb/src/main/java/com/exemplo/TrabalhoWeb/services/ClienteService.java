package com.exemplo.TrabalhoWeb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exemplo.TrabalhoWeb.entities.Cliente;
import com.exemplo.TrabalhoWeb.exception.NoSuchElementException;
import com.exemplo.TrabalhoWeb.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarClientePorId(Integer id) {
        return clienteRepository.findById(id);
    }

    public List<Cliente> buscarClientePorPartialNome(String partialNome) {
        return clienteRepository.findByPartialNomeCliente(partialNome);
    }

    public Cliente cadastrarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente atualizarCliente(Long id, Cliente cliente) {
        cliente.setIdCliente(id);
        return clienteRepository.save(cliente);
    }

    public void deletarCliente(Integer id) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        
        if (clienteOptional.isPresent()) {
            clienteRepository.deleteById(id);
        } else {
            throw new NoSuchElementException("Cliente não encontrado com o ID: " + id);
        }    }
}
