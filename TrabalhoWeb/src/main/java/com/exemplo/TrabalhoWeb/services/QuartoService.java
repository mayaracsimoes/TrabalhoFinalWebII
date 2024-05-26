package com.exemplo.TrabalhoWeb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exemplo.TrabalhoWeb.entities.Quarto;
import com.exemplo.TrabalhoWeb.exception.NoSuchElementException;
import com.exemplo.TrabalhoWeb.repository.QuartoRepository;

@Service
public class QuartoService {

    @Autowired
    private QuartoRepository quartoRepository;

    public List<Quarto> listarQuartos() {
        return quartoRepository.findAll();
    }

    public Optional<Quarto> buscarQuartoPorId(Integer id) {
        return quartoRepository.findById(id);
    }

    public List<Quarto> findByTipoQuarto(String tipoQuarto) {
        return quartoRepository.findByTipoQuarto(tipoQuarto);
    }

    public Quarto cadastrarQuarto(Quarto quarto) {
        return quartoRepository.save(quarto);
    }

    public Quarto atualizarQuarto(Long id, Quarto quarto) {
        quarto.setIdQuarto(id);
        return quartoRepository.save(quarto);
    }

    public void deletarQuarto(Integer id) {
        Optional<Quarto> quartoOptional = quartoRepository.findById(id);

        if (quartoOptional.isPresent()) {
            quartoRepository.deleteById(id);
        } else {
            throw new NoSuchElementException("Quarto não encontrado com o ID: " + id);
        }
    }

    public List<Quarto> buscarQuartoPorPartialDescricao(String nome) {
        return quartoRepository.findByPartialDescricaoQuarto(nome);
    }
}
