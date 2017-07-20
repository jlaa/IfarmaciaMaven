/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import com.mycompany.model.Aplicacao;
import com.mycompany.model.Cliente;
import com.mycompany.model.Farmacia;
import com.mycompany.model.Remedio;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 *
 * @author lucas
 */
@Named(value = "donoFarmacia")
@RequestScoped
public class donoFarmaciaBeans implements Serializable {

    @EJB
    private Aplicacao aplicacao;

    private List<Farmacia> farmacias;

    private List<Remedio> remedios;

    private List<Integer> descontos;

    @Max(value = 100, message = "O valor maximo é 100%")
    @Min(value = 1, message = "O valor minimo é 1%")
    private Integer desconto;

    public donoFarmaciaBeans() {
    }

    public List<Remedio> pegarRemedios(long id_farmacia) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("remediosOwner");
        Cliente cliente = (Cliente) SingletonSession.getInstance().getAttribute("clienteLogado");
        farmacias = cliente.getFarmacia();
        for (int i = 0; i < farmacias.size(); i++) {
            if (farmacias.get(i).getId() == (id_farmacia)) {
                remedios = farmacias.get(i).getRemedios();
                SingletonSession.getInstance().setAttribute("remediosOwner", remedios);
            }
        }
        return remedios;
    }

    public void darDesconto(Long id) {
        Remedio remedio = aplicacao.getRemedio(id);
        remedio.setDesconto(desconto);
        aplicacao.AlterarRemedio(remedio);
        Cliente cliente = (Cliente) SingletonSession.getInstance().getAttribute("clienteLogado");
        Cliente aux = aplicacao.getCliente(cliente.getEmail());
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("clienteLogado");

        SingletonSession.getInstance().setAttribute("clienteLogado", aux);

    }

    public void tirarDesconto(Long id) {
        Remedio remedio = aplicacao.getRemedio(id);
        remedio.setDesconto(0);
        aplicacao.AlterarRemedio(remedio);
        Cliente cliente = (Cliente) SingletonSession.getInstance().getAttribute("clienteLogado");
        Cliente aux = aplicacao.getCliente(cliente.getEmail());
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("clienteLogado");
        SingletonSession.getInstance().setAttribute("clienteLogado", aux);
    }

    public List<Remedio> getRemedios() {
        farmacias=getFarmacia();        
        remedios = farmacias.get(0).getRemedios();        
        return remedios;
    }

    public String setRemedios(long id) {
        pegarRemedios(id);
        return "buscarFarmacia";
    }

    public List<Farmacia> getFarmacia() {
        Cliente cliente = (Cliente) SingletonSession.getInstance().getAttribute("clienteLogado");
        farmacias = cliente.getFarmacia();
        return farmacias;
    }

    public List<Integer> listarDesconto() {
        remedios = (List<Remedio>) SingletonSession.getInstance().getAttribute("remediosOwner");
        descontos = new ArrayList();
        for (int i = 0; i < remedios.size(); i++) {
            if (remedios.get(i).getDesconto() == null) {
                descontos.add(0);
            } else {
                descontos.add(remedios.get(0).getDesconto());
            }
        }
        return descontos;
    }

    public void setDesconto(Integer desconto) {
        if (this.desconto == null) {
            this.desconto = desconto;
        }
    }

    public Integer getDesconto() {
        return desconto;
    }

    public List<Farmacia> getFarmacias() {
        return farmacias;
    }

    public void setFarmacias(List<Farmacia> farmacias) {
        this.farmacias = farmacias;
    }

    public List<Integer> getDescontos() {
        listarDesconto();
        return descontos;
    }

    public void setDescontos(List<Integer> descontos) {
        this.descontos = descontos;
    }

}
