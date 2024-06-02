package com.exemplo.TrabalhoWeb.entities;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
public class Pagamento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pagamento")
    private Long idPagamento;

    @Column(name = "data")
    private LocalDate dataPagamento;

    @Column(name = "valor")
    private Double valorPagamento;

    @Column(name = "metodo")
    private String metodoPagamento;

    @OneToOne(mappedBy = "pagamentoReserva")
    @JsonIgnore
    private Reserva reserva;

    // Construtores, getters e setters gerados automaticamente pelo Lombok
}
