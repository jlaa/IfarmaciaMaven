package com.mycompany.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.model.Aplicacao;
import com.mycompany.model.CartaoDeCredito;
import com.mycompany.model.Cliente;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author joao_dev
 */
@Named(value = "compraBean")
@RequestScoped
public class compraBean {
    @EJB
    private Aplicacao aplicacao;
    
    private List<CartaoDeCredito> cartoes;
    
    private Cliente cliente;
    
    private String estado;
    
    private String cidade;
    
    private String rua;
    
    private String numero;
    
    private String complemento;   
    
    private CartaoDeCredito cartao;
    
    /**
     * Creates a new instance of CompraBean
     */
    public compraBean()
    {
        Cliente clienteTemp = (Cliente)SingletonSession.getInstance().getAttribute("clienteLogado");      
        cartoes = clienteTemp.getCartaos();
    }

    public List<CartaoDeCredito> getCartoes() {
        return cartoes;
    }

    public void setCartoes(List<CartaoDeCredito> cartoes) {
        this.cartoes = cartoes;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public CartaoDeCredito getCartao() {
        return cartao;
    }

    public void setCartao(CartaoDeCredito cartao) {
        this.cartao = cartao;
    }
    
    public String comprarRemedio()
    {
        
        
        
        return " ";
    }
    
    
    
    
}
