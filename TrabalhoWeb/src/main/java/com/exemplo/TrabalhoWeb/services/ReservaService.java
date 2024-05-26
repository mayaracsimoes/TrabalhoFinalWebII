package com.exemplo.TrabalhoWeb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exemplo.TrabalhoWeb.entities.Reserva;
import com.exemplo.TrabalhoWeb.exception.NoSuchElementException;
import com.exemplo.TrabalhoWeb.repository.ReservaRepository;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    public List<Reserva> listarReservas() {
        return reservaRepository.findAll();
    }

    public Optional<Reserva> buscarReservaPorId(Integer id) {
        return reservaRepository.findById(id);
    }

    public Reserva cadastrarReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    public Reserva atualizarReserva(Long id, Reserva reserva) {
        reserva.setIdReserva(id);
        return reservaRepository.save(reserva);
    }

    public void deletarReserva(Integer id) {
        Optional<Reserva> reservOptional = reservaRepository.findById(id);

        if (reservOptional.isPresent()) {
            reservaRepository.deleteById(id);
        } else {
            throw new NoSuchElementException("Reserva não encontrada com o ID: " + id);
        }
    }

    public List<Reserva> buscarReservaPorCliente(Integer cliente) {
        return reservaRepository.findByClienteReserva_IdCliente(cliente);
    }

    public List<Reserva> buscarReservaPorQuarto(String quarto) {
        return reservaRepository.findByQuartoReserva_numeroQuarto(quarto);
    }

    public List<Reserva> buscarReservaPorHotel(Integer hotel) {
        return reservaRepository.findByHotelReserva_IdHotel(hotel);
    }
}
