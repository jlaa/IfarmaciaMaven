/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.Future;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author joao_dev
 */
@Entity

@Table(name = "CARTAO_TABLE")
@Access(AccessType.FIELD)
public class CartaoDeCredito implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CARTAO", nullable = false)
    private Long id_cartao;

    @ValidaBandeiraCartao
    @Column(name = "TXT_BANDEIRA", nullable = false)
    private String bandeira;

    @CreditCardNumber
    @Column(name = "TXT_NUMERO", nullable = false)
    private String numero_cartao;

    //PROCURAR DEPOIS
    @Future
    @Temporal(TemporalType.DATE)
    @Column(name = "DT_EXPIRACAO", nullable = false)
    private Calendar dataExpiracao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CARTAO_CLIENTE", referencedColumnName = "ID_USUARIO")
    private Cliente cliente;
    //valores estão no validador bandeira cartao

    public CartaoDeCredito() {
        
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    public String getNumero_cartao() {
        return numero_cartao;
    }

    public void setNumero_cartao(String numero_cartao) {
        this.numero_cartao = numero_cartao;
    }

    public Calendar getDataExpiracao() {
        return dataExpiracao;
    }

    public void setDataExpiracao(Calendar dataExpiracao) {
        this.dataExpiracao = dataExpiracao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Long getId_cartao() {
        return id_cartao;
    }

    public void setId_cartao(Long id_cartao) {
        this.id_cartao = id_cartao;
    }

    

}
