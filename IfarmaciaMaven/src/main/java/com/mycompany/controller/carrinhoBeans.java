/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.model.Aplicacao;
import com.mycompany.model.Remedio;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author LucasPC
 */
@Named(value = "carrinhoBeans")
@SessionScoped
public class carrinhoBeans implements Serializable {

    private String nomeDoRemedio;
    private double preco;
    private String nomeDaFarmacia;
    private List<Remedio> remedios;

    /**
     * Creates a new instance of carrinhoBeans
     */
    public carrinhoBeans() 
    {
        remedios = (List<Remedio>)SingletonSession.getInstance().getAttribute("remedios");
    }

    public String getNomeDoRemedio() {
        return nomeDoRemedio;
    }

    public void setNomeDoRemedio(String nomeDoRemedio) {
        this.nomeDoRemedio = nomeDoRemedio;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getNomeDaFarmacia() {
        return nomeDaFarmacia;
    }

    public void setNomeDaFarmacia(String nomeDaFarmacia) {
        this.nomeDaFarmacia = nomeDaFarmacia;
    }
    

    public String adicionarAoCarrinho(Long id) {
        Aplicacao aplicacao = new Aplicacao();
        Remedio remedio = aplicacao.getRemedio(id);
        remedios.add(remedio);
        SingletonSession.getInstance().setAttribute("remedios",remedios);        
        return "Carrinho";    
    }
    
    public void retirarDoCarrinho(Long id)
    {
        
    }
    
    public List<Remedio> getRemedios()
    {
        return remedios;
    }

}
