package com.exemplo.TrabalhoWeb.entities;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class Reserva implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva")
    private Long idReserva;

    @Column(name = "datini_reserva")
    private LocalDate dataInicioReserva;

    @Column(name = "datfim_reserva")
    private LocalDate dataFimReserva;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente clienteReserva;

    @ManyToOne
    @JoinColumn(name = "id_quarto")
    private Quarto quartoReserva;

    @ManyToOne
    @JoinColumn(name = "id_hotel")
    private Hotel hotelReserva;

    @OneToOne
    @JoinColumn(name = "id_pagamento")
    private Pagamento pagamentoReserva;

    // Construtores, getters e setters gerados automaticamente pelo Lombok
}
