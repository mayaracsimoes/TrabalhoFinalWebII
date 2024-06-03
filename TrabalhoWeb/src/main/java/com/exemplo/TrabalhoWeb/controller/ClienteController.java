package com.exemplo.TrabalhoWeb.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exemplo.TrabalhoWeb.entities.Cliente;
import com.exemplo.TrabalhoWeb.exception.NoSuchElementException;
import com.exemplo.TrabalhoWeb.services.ClienteService;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteController {
    @Autowired
    private ClienteService cService;

    @GetMapping
    public ResponseEntity <List<Cliente>> listarClientes() {
        List<Cliente> clientes = cService.listarClientes();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarClientePorId(@PathVariable Integer id) {
        Optional<Cliente> cliente = cService.buscarClientePorId(id);
        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado com o ID: " + id);
        }
    }

    @GetMapping("/buscarPorNome")
    public ResponseEntity<?> buscarClientePorNome(@RequestParam String nome) {
        List<Cliente> clientes = cService.buscarClientePorPartialNome(nome);
        if (!clientes.isEmpty()) {
            return ResponseEntity.ok(clientes);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Nenhum cliente encontrado com o nome que contenha: " + nome);
        }
    }

    @PostMapping()
    public ResponseEntity<Cliente> cadastrarCliente(@RequestBody Cliente clienteDetails) {
        Cliente cliente = cService.cadastrarCliente(clienteDetails);
        return ResponseEntity.ok().body(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarCliente(@PathVariable Long id, @RequestBody Cliente clienteAtualizado) {
        Cliente cliente = cService.atualizarCliente(id, clienteAtualizado);
        if (cliente != null) {
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado com o ID: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deletarCliente(@PathVariable Integer id) {
        try {
            cService.deletarCliente(id);
            return ResponseEntity.ok(Map.of("status", "OK", "mensagem", "Cliente deletado com sucesso."));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(Map.of("status", "ERRO", "mensagem", "Cliente não encontrado com o ID: " + id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(Map.of("status", "ERRO", "mensagem", e.getMessage()));
        }
    }
}
