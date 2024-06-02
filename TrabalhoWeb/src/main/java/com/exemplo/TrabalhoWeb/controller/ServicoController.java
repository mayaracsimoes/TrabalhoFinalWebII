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

import com.exemplo.TrabalhoWeb.entities.Reserva;
import com.exemplo.TrabalhoWeb.entities.Servico;
import com.exemplo.TrabalhoWeb.exception.NoSuchElementException;
import com.exemplo.TrabalhoWeb.services.ReservaService;
import com.exemplo.TrabalhoWeb.services.ServicoService;

@RestController
@RequestMapping("/servico")
public class ServicoController {
    @Autowired
    private ServicoService servicoService;
    @Autowired
    private ReservaService reservaService;

    @GetMapping
    public ResponseEntity<List<Servico>> listarServicos() {
        List<Servico> servicos = servicoService.listarServicos();
        return new ResponseEntity<>(servicos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarServicoPorId(@PathVariable Long id) {
        Optional<Servico> servico = servicoService.buscarServicoPorId(id);
        if (servico.isPresent()) {
            return ResponseEntity.ok(servico);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Serviço não encontrado com o ID: " + id);
        }
    }

    @GetMapping("/buscarPorNome")
    public ResponseEntity<?> getServicoByNome(@RequestParam String nomeServico) {
        List<Servico> servicos = servicoService.buscarServicosPorNome(nomeServico);
        if (!servicos.isEmpty()) {
            return ResponseEntity.ok(servicos);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Nenhum serviço encontrado com o nome: " + nomeServico);
        }
    }

    @GetMapping("/reserva/{id}")
    public ResponseEntity<List<Servico>> buscarServicosPorReserva(@PathVariable Integer id) {
        Optional<Reserva> reserva = reservaService.buscarReservaPorId(id);
        if (reserva.isPresent()) {
            List<Servico> servicos = servicoService.buscarServicosPorReserva(reserva.get());
            return new ResponseEntity<>(servicos, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Servico> salvarServico(@RequestBody Servico servico) {
        Servico novoServico = servicoService.salvarServico(servico);
        return new ResponseEntity<>(novoServico, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarServico(@PathVariable Long id, @RequestBody Servico servicoAtualizado) {
        Servico servico = servicoService.atualizarServico(id, servicoAtualizado);
        if (servico != null) {
            return new ResponseEntity<>(servico, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Serviço não encontrado com o ID: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deletarServico(@PathVariable Long id) {
        try {
            servicoService.deletarServicoPorId(id);
            return ResponseEntity.ok(Map.of("status", "OK", "mensagem", "Serviço deletado com sucesso."));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("status", "ERRO", "mensagem", "Serviço não encontrado com o ID: " + id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("status", "ERRO", "mensagem", e.getMessage()));
        }
    }
}
