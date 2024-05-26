package com.exemplo.TrabalhoWeb.entities;

import java.io.Serializable;
import java.util.List;

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
public class Hotel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_hotel")
    private Long idHotel;

    @Column(name = "nome")
    private String nomeHotel;

    @Column(name = "localidade")
    private String localizacaoHotel;

    @Column(name = "endereco")
    private String enderecoHotel;

    @Column(name = "telefone")
    private String telefoneHotel;

    @Column(name = "email")
    private String emailHotel;

    @Column(name = "website")
    private String websiteHotel;

    @OneToMany(mappedBy = "hotelReserva")
    private List<Reserva> reservas;
}
