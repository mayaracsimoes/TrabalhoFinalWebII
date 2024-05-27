package com.exemplo.TrabalhoWeb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exemplo.TrabalhoWeb.entities.Pagamento;
import com.exemplo.TrabalhoWeb.exception.NoSuchElementException;
import com.exemplo.TrabalhoWeb.repository.PagamentoRepository;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    public List<Pagamento> listarPagamentos() {
        return pagamentoRepository.findAll();
    }

    public Optional<Pagamento> buscarPagamentoPorId(Integer id) {
        return pagamentoRepository.findById(id);
    }

    public Pagamento cadastrarPagamento(Pagamento pagamento) {
        return pagamentoRepository.save(pagamento);
    }

    public Pagamento atualizarPagamento(Long id, Pagamento pagamento) {
        pagamento.setIdPagamento(id);
        return pagamentoRepository.save(pagamento);
    }

    public void deletarPagamento(Integer id) {
        Optional<Pagamento> pagamentoOptional = pagamentoRepository.findById(id);

        if (pagamentoOptional.isPresent()) {
            pagamentoRepository.deleteById(id);
        } else {
            throw new NoSuchElementException("Pagamento não encontrado com o ID: " + id);
        }
    }
}
