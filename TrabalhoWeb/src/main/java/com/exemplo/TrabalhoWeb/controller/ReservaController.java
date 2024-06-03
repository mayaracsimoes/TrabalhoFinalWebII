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
import com.exemplo.TrabalhoWeb.exception.NoSuchElementException;
import com.exemplo.TrabalhoWeb.services.ReservaService;

@RestController

@RequestMapping("/reserva")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @GetMapping
    public ResponseEntity<List<Reserva>> listarReservas() {
        List<Reserva> reservas = reservaService.listarReservas();
        return new ResponseEntity<>(reservas, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarReservaPorId(@PathVariable Integer id) {
        Optional<Reserva> reserva = reservaService.buscarReservaPorId(id);
        if (reserva.isPresent()) {
            return ResponseEntity.ok(reserva);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reserva não encontrada com o ID: " + id);
        }
    }

    @GetMapping("/buscarPorIdCliente")
    public ResponseEntity<?> buscarReservaPorCliente(@RequestParam Integer cliente) {
        List<Reserva> reservas = reservaService.buscarReservaPorCliente(cliente);
        if (!reservas.isEmpty()) {
            return ResponseEntity.ok(reservas);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Nenhuma reserva encontrada para o cliente: " + cliente);
        }
    }

    @GetMapping("/buscarPorQuarto")
    public ResponseEntity<?> buscarReservaPorQuarto(@RequestParam String quarto) {
        List<Reserva> reservas = reservaService.buscarReservaPorQuarto(quarto);
        if (!reservas.isEmpty()) {
            return ResponseEntity.ok(reservas);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Nenhuma reserva encontrada para o quarto: " + quarto);
        }
    }

    @GetMapping("/buscarPorIdHotel")
    public ResponseEntity<?> buscarReservaPorHotel(@RequestParam Integer hotel) {
        List<Reserva> reservas = reservaService.buscarReservaPorHotel(hotel);
        if (!reservas.isEmpty()) {
            return ResponseEntity.ok(reservas);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Nenhuma reserva encontrada para o hotel: " + hotel);
        }
    }

    @PostMapping
    public ResponseEntity<?> cadastrarReserva(@RequestBody Reserva reserva) {
        try {
            Reserva novaReserva = reservaService.cadastrarReserva(reserva);
            return new ResponseEntity<>(novaReserva, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao inserir reserva. Detalhes: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarReserva(@PathVariable Long id, @RequestBody Reserva reservaAtualizada) {
        System.out.println(id);
        Reserva reserva = reservaService.atualizarReserva(id, reservaAtualizada);
        if (reserva != null) {
            return new ResponseEntity<>(reserva, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reserva não encontrada com o ID: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deletarReserva(@PathVariable Integer id) {
        try {
            reservaService.deletarReserva(id);
            return ResponseEntity.ok(Map.of("status", "OK", "mensagem", "Reserva deletada com sucesso."));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("status", "ERRO", "mensagem", "Reserva não encontrada com o ID: " + id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("status", "ERRO", "mensagem", e.getMessage()));
        }
    }
}
