package com.exemplo.TrabalhoWeb.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exemplo.TrabalhoWeb.entities.Pagamento;
import com.exemplo.TrabalhoWeb.entities.Reserva;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {
    List<Pagamento> findByDataPagamento(LocalDate dataPagamento);

    List<Pagamento> findByIdPagamento(Integer idPagamento);

    List<Pagamento> findByReserva(Reserva reserva);
}
