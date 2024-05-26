package com.exemplo.TrabalhoWeb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.exemplo.TrabalhoWeb.entities.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {
    List<Hotel> findByNomeHotel(String nomeHotel);

    List<Hotel> findByIdHotel(Integer idHotel);

    @Query("SELECT h FROM Hotel h WHERE h.nomeHotel LIKE %?1%")
    List<Hotel> findByPartialNomeHotel(String partialNomeHotel);
}