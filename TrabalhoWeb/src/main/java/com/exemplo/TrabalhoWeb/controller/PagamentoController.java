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
import org.springframework.web.bind.annotation.RestController;

import com.exemplo.TrabalhoWeb.entities.Pagamento;
import com.exemplo.TrabalhoWeb.exception.NoSuchElementException;
import com.exemplo.TrabalhoWeb.services.PagamentoService;

@RestController
@RequestMapping("/pagamento")
public class PagamentoController {
    
    @Autowired
    private PagamentoService pagamentoService;

    @GetMapping
    public ResponseEntity<List<Pagamento>> listarPagamentos() {
        List<Pagamento> pagamentos = pagamentoService.listarPagamentos();
        return new ResponseEntity<>(pagamentos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPagamentoPorId(@PathVariable Integer id) {
        Optional<Pagamento> pagamento = pagamentoService.buscarPagamentoPorId(id);
        if (pagamento.isPresent()) {
            return ResponseEntity.ok(pagamento);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pagamento não encontrado com o ID: " + id);
        }
    }

    @PostMapping
    public ResponseEntity<Pagamento> cadastrarPagamento(@RequestBody Pagamento pagamento) {
        Pagamento novoPagamento = pagamentoService.cadastrarPagamento(pagamento);
        return new ResponseEntity<>(novoPagamento, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pagamento> atualizarPagamento(@PathVariable Long id,
            @RequestBody Pagamento pagamentoAtualizado) {
        Pagamento pagamento = pagamentoService.atualizarPagamento(id, pagamentoAtualizado);
        if (pagamento != null) {
            return new ResponseEntity<>(pagamento, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deletarPagamento(@PathVariable Integer id) {
        try {
            pagamentoService.deletarPagamento(id);
            return ResponseEntity.ok(Map.of("status", "OK", "mensagem", "Pagamento deletado com sucesso."));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("status", "ERRO", "mensagem", "Pagamento não encontrado com o ID: " + id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("status", "ERRO", "mensagem", e.getMessage()));
        }
    }

}
