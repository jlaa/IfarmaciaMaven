/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.model.Aplicacao;
import com.mycompany.model.CartaoDeCredito;
import com.mycompany.model.Cliente;
import com.mycompany.model.ValidaBandeiraCartao;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Future;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author LucasPC
 */
@Named(value = "cadastrarCartaoBeans")
@RequestScoped
public class cadastrarCartaoBeans {
    @EJB
    private Aplicacao aplicacao;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_cartao;

    @ValidaBandeiraCartao(message = "Não é uma bandeira válida")
    @NotBlank(message = "Favor escolha um cartão")
    private String bandeira;

    @CreditCardNumber(message = "Não é um cartão válido")
    @NotBlank(message = "Favor digite um número válido de cartão")
    private String numeroCartao;

    @Future(message = "Digite uma data válida")
    @Temporal(TemporalType.DATE)
    private Date dataExpiracao;

    private List<String> cartao;

    public Long getId_cartao() {
        return id_cartao;
    }

    public void setId_cartao(Long id_cartao) {
        this.id_cartao = id_cartao;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public Date getDataExpiracao() {
        return dataExpiracao;
    }

    public void setDataExpiracao(Date dataExpiracao) {
        this.dataExpiracao = dataExpiracao;
    }

    /**
     * Creates a new instance of cadastrarCartaoBeans
     */
    public cadastrarCartaoBeans() {
        this.cartao = new ArrayList<>();
        this.cartao.add("Visa");
        this.cartao.add("MasterCard");
        this.cartao.add("DinersClub");
        this.cartao.add("AmericanExpress");
        this.cartao.add("HiperCard");
        this.cartao.add("Aura");
        this.cartao.add("Elo");
        this.cartao.add("Amex");
    }

    public String cadastrarCartao() {

        CartaoDeCredito cartao = new CartaoDeCredito();

        cartao.setBandeira(bandeira);

        Calendar cal = null;
        try {
            DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            String dataCerta = formatter.format(dataExpiracao);
            dataExpiracao = (Date) formatter.parse(dataCerta);
            cal = Calendar.getInstance();
            cal.setTime(dataExpiracao);
        } catch (ParseException e) {
            System.out.println("Exception :" + e);
        }
        cartao.setDataExpiracao(cal);
        cartao.setNumero_cartao(numeroCartao);
        Cliente cliente = (Cliente) SingletonSession.getInstance().getAttribute("clienteLogado");
        cartao.setCliente(cliente);
        cliente.setCartao(cartao);
        aplicacao.inserirCartao(cliente);
        return "Index";
    }

    public List<String> listaBandeiraCartao() {
        return this.cartao;
    }

    public Date getToday() {
        return new Date();
    }

}
