package com.exemplo.TrabalhoWeb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exemplo.TrabalhoWeb.entities.Reserva;
import com.exemplo.TrabalhoWeb.entities.Servico;
import com.exemplo.TrabalhoWeb.exception.NoSuchElementException;
import com.exemplo.TrabalhoWeb.repository.ServicoRepository;

@Service
public class ServicoService {

    @Autowired
    ServicoRepository servicoRepository;

    public List<Servico> listarServicos() {
        return servicoRepository.findAll();
    }

    public Optional<Servico> buscarServicoPorId(Long id) {
        return servicoRepository.findById(id);
    }

    public Servico salvarServico(Servico servico) {
        return servicoRepository.save(servico);
    }

    public Servico atualizarServico(Long id, Servico servico) {
        servico.setIdServico(id);
        return servicoRepository.save(servico);
    }

    public void deletarServicoPorId(Long id) {
        Optional<Servico> servicOptional = servicoRepository.findById(id);

        if (servicOptional.isPresent()) {
            servicoRepository.deleteById(id);
        } else {
            throw new NoSuchElementException("Serviço não encontrado com o ID: " + id);
        }
    }

    public List<Servico> buscarServicosPorNome(String nomeServico) {
        return servicoRepository.findByNomeServico(nomeServico);
    }

    public List<Servico> buscarServicosPorReserva(Reserva reserva) {
        return servicoRepository.findByReservas(reserva);
    }
}
