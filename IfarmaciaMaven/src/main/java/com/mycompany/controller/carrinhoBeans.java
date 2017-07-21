/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.model.Aplicacao;
import com.mycompany.model.Remedio;
import com.mycompany.model.Cliente;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
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
    private double total;
    private FacesContext facesContext;

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
        Remedio remedio = aplicacao.getRemedio(id);
        for (int i = 0; i < remedioComprado.size(); i++) {
            if (!remedioComprado.get(i).getId().equals(remedio.getId())) {
                //só existe um item no carrinho
                remedio.setQuantidade(1);
                remedioComprado.add(remedio);
                SingletonSession.getInstance().setAttribute("remedioComprado", remedioComprado);
                break;
            } else {
                //dizer que esse item já foi adicionado
                return " ";
            }
        }
        if (remedioComprado.isEmpty()) {
            remedio.setQuantidade(1);
            remedioComprado.add(remedio);
            SingletonSession.getInstance().setAttribute("remedioComprado", remedioComprado);

        }
        return "sucessoCarrinho";
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

    public void adicionarQuantidade(int indice) {
        remedioComprado = (List<Remedio>) SingletonSession.getInstance().getAttribute("remedioComprado");
        Remedio remedio = remedioComprado.get(indice);
        Remedio temp = aplicacao.getRemedio(remedio.getId());
        if (temp.getQuantidade() > remedio.getQuantidade()) {
            remedioComprado.get(indice).aumentaEstoque(1);
            SingletonSession.getInstance().setAttribute("remedioComprado", remedioComprado);
        }

    }

    public void diminuirQuantidade(int indice) {
        remedioComprado = (List<Remedio>) SingletonSession.getInstance().getAttribute("remedioComprado");
        Remedio remedio = remedioComprado.get(indice);
        if (remedio.getQuantidade() > 1) {
            remedioComprado.get(indice).diminuiEstoque(1);
            SingletonSession.getInstance().setAttribute("remedioComprado", remedioComprado);
        }
    }

    public String comprarRemedio() {
        Cliente cliente = (Cliente) (Cliente) SingletonSession.getInstance().getAttribute("clienteLogado");
        String retorno;
        List<Remedio> remedio = (List<Remedio>) SingletonSession.getInstance().getAttribute("remedioComprado");
        if (remedio == null||remedio.isEmpty()) {
            
            retorno = null;
            facesContext = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Não há remédios no carrinho", null);
            facesContext.addMessage(null, message);
        } else {
            if (cliente.getCartaos().isEmpty()) {
                retorno = "cartao";
                return retorno;
            } else {
                retorno = "comprarRemedio";
            }
        }
        return retorno;
    }

    public List<Remedio> getRemedios() {
        return remedioComprado;
    }

    public double getPegaPrecos() {
        total = 0;
        List<Remedio> remediosTemp = (List<Remedio>) SingletonSession.getInstance().getAttribute("remedioComprado");
        if (remediosTemp != null) {
            for (int i = 0; i < remediosTemp.size(); i++) {
                total = total + (remediosTemp.get(i).getQuantidade() * remediosTemp.get(i).getPreco());
            }
        }
        return total;
    }

    public double getPegaPrecosDesconto() {
        total = 0;
        List<Remedio> remediosTemp = (List<Remedio>) SingletonSession.getInstance().getAttribute("remedioComprado");
        if (remediosTemp != null) {
            for (int i = 0; i < remediosTemp.size(); i++) {
                double preco = remediosTemp.get(i).getPreco()
                        - ((remediosTemp.get(i).getPreco() * remediosTemp.get(i).getDesconto()) / 100);
                total = total + (remediosTemp.get(i).getQuantidade() * preco);
            }
        }
        return total;
    }

}
