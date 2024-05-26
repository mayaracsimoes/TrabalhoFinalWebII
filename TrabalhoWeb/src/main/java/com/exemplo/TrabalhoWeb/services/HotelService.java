package com.exemplo.TrabalhoWeb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exemplo.TrabalhoWeb.entities.Hotel;
import com.exemplo.TrabalhoWeb.exception.NoSuchElementException;
import com.exemplo.TrabalhoWeb.repository.HotelRepository;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    public List<Hotel> listarHoteis() {
        return hotelRepository.findAll();
    }

    public Optional<Hotel> buscarHotelPorId(Integer id) {
        return hotelRepository.findById(id);
    }

    public Hotel cadastrarHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    public Hotel atualizarHotel(Long id, Hotel hotel) {
        hotel.setIdHotel(id);
        return hotelRepository.save(hotel);
    }

   public void deletarHotel(Integer id) {
        Optional<Hotel> hotelOptional = hotelRepository.findById(id);

        if (hotelOptional.isPresent()) {
            hotelRepository.deleteById(id);
        } else {
            throw new NoSuchElementException("Hotel não encontrado com o ID: " + id);
        }
    }

    public List<Hotel> buscarHotelPorPartialNome(String nome) {
        return hotelRepository.findByPartialNomeHotel(nome);
    }
}