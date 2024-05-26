package com.exemplo.TrabalhoWeb.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exemplo.TrabalhoWeb.entities.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer> {

    List<Reserva> findByDataFimReserva(LocalDate dataFimReserva);

    List<Reserva> findByDataInicioReserva(LocalDate dataInicioReserva);

    List<Reserva> findByIdReserva(Integer idReserva);

    List<Reserva> findByClienteReserva_IdCliente(Integer clienteReserva);

    List<Reserva> findByHotelReserva_IdHotel(Integer hotelReserva);

    List<Reserva> findByQuartoReserva_numeroQuarto(String quartoReserva);
}
