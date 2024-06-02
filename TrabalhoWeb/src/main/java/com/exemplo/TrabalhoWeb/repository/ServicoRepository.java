package com.exemplo.TrabalhoWeb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.exemplo.TrabalhoWeb.entities.Reserva;
import com.exemplo.TrabalhoWeb.entities.Servico;

public interface ServicoRepository extends JpaRepository<Servico, Long> {
    @Query("SELECT q FROM Servico q WHERE q.nomeServico LIKE %?1%")
    List<Servico> findByNomeServico(String nomeServico);

    List<Servico> findByReservas(Reserva reserva);
}
