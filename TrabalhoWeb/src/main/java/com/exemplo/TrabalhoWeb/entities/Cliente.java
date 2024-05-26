package com.exemplo.TrabalhoWeb.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long idCliente;
    
    @Column(name = "nome")
    private String nomeCliente;
    @NotEmpty(message="Nome do cliente não pode ser vazio!")
    @NotNull(message = "Nome do cliente não pode ser nulo!")

    @Column(name = "email")
    private String emailCliente;

    @Column(name = "telefone")
    private String telefoneCliente;

    @Column(name = "endereco")
    private String enderecoCliente;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimentoCliente;

    @Column(name = "cpf")
    private String cpfCliente;
    
    @OneToMany(mappedBy = "clienteReserva")
    private List<Reserva> reservas;
    // Construtores, getters e setters gerados automaticamente pelo Lombok
}
