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

import com.exemplo.TrabalhoWeb.entities.Quarto;
import com.exemplo.TrabalhoWeb.exception.NoSuchElementException;
import com.exemplo.TrabalhoWeb.services.QuartoService;

@RestController
@RequestMapping("/quarto")
public class QuartoController {

    @Autowired
    private QuartoService quartoService;

    @GetMapping
    public ResponseEntity<List<Quarto>> listarQuartos() {
        List<Quarto> quartos = quartoService.listarQuartos();
        return new ResponseEntity<>(quartos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarQuartoPorId(@PathVariable Integer id) {
        Optional<Quarto> quarto = quartoService.buscarQuartoPorId(id);
        if (quarto.isPresent()) {
            return ResponseEntity.ok(quarto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Quarto não encontrado com o ID: " + id);
        }
    }

    @GetMapping("/buscarPorDescricao")
    public ResponseEntity<?> buscarQuartoPorDescricao(@RequestParam String nome) {
        List<Quarto> quartos = quartoService.buscarQuartoPorPartialDescricao(nome);
        if (!quartos.isEmpty()) {
            return ResponseEntity.ok(quartos);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Nenhum quarto encontrado com a descrição que contenha: " + nome);
        }
    }

    @GetMapping("/buscarPorTipo")
    public ResponseEntity<?> getQuartosByTipo(@RequestParam String tipoQuarto) {
        List<Quarto> quartos = quartoService.findByTipoQuarto(tipoQuarto);
        if (!quartos.isEmpty()) {
            return ResponseEntity.ok(quartos);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Nenhum quarto encontrado com o tipo: " + tipoQuarto);
        }
    }

    @PostMapping
    public ResponseEntity<Quarto> cadastrarQuarto(@RequestBody Quarto quarto) {
        Quarto novoQuarto = quartoService.cadastrarQuarto(quarto);
        return new ResponseEntity<>(novoQuarto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarQuarto(@PathVariable Long id, @RequestBody Quarto quartoAtualizado) {
        Quarto quarto = quartoService.atualizarQuarto(id, quartoAtualizado);
        if (quarto != null) {
            return new ResponseEntity<>(quarto, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Quarto não encontrado com o ID: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deletarQuarto(@PathVariable Integer id) {
        try {
            quartoService.deletarQuarto(id);
            return ResponseEntity.ok(Map.of("status", "OK", "mensagem", "Quarto deletado com sucesso."));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("status", "ERRO", "mensagem", "Quarto não encontrado com o ID: " + id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("status", "ERRO", "mensagem", e.getMessage()));
        }
    }
}
