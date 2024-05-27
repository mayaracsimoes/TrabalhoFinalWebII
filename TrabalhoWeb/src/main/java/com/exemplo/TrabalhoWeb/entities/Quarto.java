package com.exemplo.TrabalhoWeb.entities;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
public class Quarto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_quarto")
    private Long idQuarto;

    @Column(name = "numero")
    private String numeroQuarto;

    @Column(name = "tipo")
    private String tipoQuarto;

    @Column(name = "preco_diario")
    private Double precoDiariaQuarto;

    @Column(name = "descricao")
    private String descricaoQuarto;

    @OneToMany(mappedBy = "quartoReserva")
    @JsonIgnore // Ignora a serialização desta propriedade
    private List<Reserva> reservas;

    // Construtores, getters e setters gerados automaticamente pelo Lombok
}
