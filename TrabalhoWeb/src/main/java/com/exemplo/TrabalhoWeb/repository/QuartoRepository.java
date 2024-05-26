package com.exemplo.TrabalhoWeb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.exemplo.TrabalhoWeb.entities.Quarto;

@Repository
public interface QuartoRepository extends JpaRepository<Quarto, Integer> {

    @Query("SELECT q FROM Quarto q WHERE q.tipoQuarto LIKE %?1%")
    List<Quarto> findByTipoQuarto(String tipoQuarto);

    List<Quarto> findByIdQuarto(Integer idQuarto);

    List<Quarto> findByNumeroQuarto(String numeroQuarto);

    @Query("SELECT q FROM Quarto q WHERE q.descricaoQuarto LIKE %?1%")
    List<Quarto> findByPartialDescricaoQuarto(String descricaoQuarto);

}
