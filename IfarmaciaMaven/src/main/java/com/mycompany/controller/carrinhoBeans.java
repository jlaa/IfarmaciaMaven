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
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;

/**
 *
 * @author LucasPC
 */
@Named(value = "carrinhoBeans")
@SessionScoped
public class carrinhoBeans implements Serializable {

    @EJB
    private Aplicacao aplicacao;
    private String nomeDoRemedio;
    private double preco;
    private String nomeDaFarmacia;
    private List<Remedio> remedioComprado = new ArrayList();

    /**
     * Creates a new instance of carrinhoBeans
     */
    public carrinhoBeans() {
        remedioComprado = (List<Remedio>) SingletonSession.getInstance().getAttribute("remedioComprado");
        if (remedioComprado == null) {
            remedioComprado = new ArrayList();
        }
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
        if (SingletonSession.getInstance().getAttribute("clienteLogado") == null) {
            return "Login?faces-redirect=true";
        }
        Remedio remedio = aplicacao.getRemedio(id);
        for (int i = 0; i < remedioComprado.size(); i++) {
            if (!remedioComprado.get(i).getId().equals(remedio.getId())) {
                remedioComprado.add(remedio);
                SingletonSession.getInstance().setAttribute("remedioComprado", remedioComprado);
                break;
            } else {
                //dizer que esse item já foi adicionado
                return " ";
            }
        }
        if (remedioComprado.isEmpty()) {
            remedioComprado.add(remedio);
            SingletonSession.getInstance().setAttribute("remedioComprado", remedioComprado);

        }
        return "Carrinho?faces-redirect=true";
    }

    public String retirarDoCarrinho(Long id) {
        Remedio remedio = aplicacao.getRemedio(id);

        for (int i = 0; i < remedioComprado.size(); i++) {
            if (remedioComprado.get(i).getId().equals(id)) {
                remedioComprado.remove(i);
                SingletonSession.getInstance().setAttribute("remedioComprado", remedioComprado);
                break;
            }
        }
        return " ";
    }

    public String comprarRemedio() {
        return " ";
    }

    public List<Remedio> getRemedios() {
        return remedioComprado;
    }

}
