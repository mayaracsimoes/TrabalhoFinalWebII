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

import com.exemplo.TrabalhoWeb.entities.Hotel;
import com.exemplo.TrabalhoWeb.exception.NoSuchElementException;
import com.exemplo.TrabalhoWeb.services.HotelService;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @GetMapping
    public ResponseEntity<List<Hotel>> listarHoteis() {
        List<Hotel> hoteis = hotelService.listarHoteis();
        return new ResponseEntity<>(hoteis, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarHotelPorId(@PathVariable Integer id) {
        Optional<Hotel> hotel = hotelService.buscarHotelPorId(id);
        if (hotel.isPresent()) {
            return ResponseEntity.ok(hotel);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hotel não encontrado com o ID: " + id);
        }
    }

    @GetMapping("/buscarPorNome")
    public ResponseEntity<?> buscarHotelPorNome(@RequestParam String nome) {
        List<Hotel> hoteis = hotelService.buscarHotelPorPartialNome(nome);
        if (!hoteis.isEmpty()) {
            return ResponseEntity.ok(hoteis);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Nenhum hotel encontrado com o nome que contenha: " + nome);
        }
    }

    @PostMapping
    public ResponseEntity<Hotel> cadastrarHotel(@RequestBody Hotel hotel) {
        Hotel novoHotel = hotelService.cadastrarHotel(hotel);
        return new ResponseEntity<>(novoHotel, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarHotel(@PathVariable Long id, @RequestBody Hotel hotelAtualizado) {
        Hotel hotel = hotelService.atualizarHotel(id, hotelAtualizado);
        if (hotel != null) {
            return new ResponseEntity<>(hotel, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hotel não encontrado com o ID: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deletarHotel(@PathVariable Integer id) {
        try {
            hotelService.deletarHotel(id);
            return ResponseEntity.ok(Map.of("status", "OK", "mensagem", "Hotel deletado com sucesso."));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("status", "ERRO", "mensagem", "Hotel não encontrado com o ID: " + id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("status", "ERRO", "mensagem", e.getMessage()));
        }
    }

}
