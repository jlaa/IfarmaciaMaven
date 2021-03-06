/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
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
        SingletonSession.getInstance().setAttribute("clienteLogado", aux);
        Farmacia farmacia = remedio.getFarmacia().get(0);
        SingletonSession.getInstance().setAttribute("remediosOwner", farmacia.getRemedios());

    }

    public void tirarDesconto(Long id) {
        Remedio remedio = aplicacao.getRemedio(id);
        remedio.setDesconto(0);
        aplicacao.AlterarRemedio(remedio);
        Cliente cliente = (Cliente) SingletonSession.getInstance().getAttribute("clienteLogado");
        Cliente aux = aplicacao.getCliente(cliente.getEmail());
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("clienteLogado");
        SingletonSession.getInstance().setAttribute("clienteLogado", aux);
        Farmacia farmacia = remedio.getFarmacia().get(0);
        SingletonSession.getInstance().setAttribute("remediosOwner", farmacia.getRemedios());
    }

    public void removeRemedio(Long id) {
        Remedio remedio = aplicacao.getRemedio(id);
        remedios = (List<Remedio>) SingletonSession.getInstance().getAttribute("remediosOwner");
        for(int i=0;i<remedios.size();i++)
        {
            if(remedios.get(i).getId().equals(remedio.getId()))
            {
                remedios.remove(i);
            }
        }
        aplicacao.excluirRemedio(remedio);
        SingletonSession.getInstance().setAttribute("remediosOwner", remedios);
        Cliente cliente = (Cliente) SingletonSession.getInstance().getAttribute("clienteLogado");
        Cliente aux = aplicacao.getCliente(cliente.getEmail());
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("clienteLogado");
        SingletonSession.getInstance().setAttribute("clienteLogado", aux);
    }    
    
    public void removeFarmacia(Long id) {
        Farmacia farmacia = aplicacao.getFarmacia(id);
        Cliente cliente = (Cliente) SingletonSession.getInstance().getAttribute("clienteLogado");        
        farmacias = cliente.getFarmacia();
        for(int i=0;i<farmacias.size();i++)
        {
            if(farmacias.get(i).getId()==farmacia.getId())
            {
                farmacias.remove(i);
            }
        }
        aplicacao.excluirFarmacia(farmacia);
        cliente.setFarmacia(farmacias);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("clienteLogado");
        SingletonSession.getInstance().setAttribute("clienteLogado", cliente);
    }    
  

    public List<Remedio> getRemedios() {
        remedios = (List<Remedio>) SingletonSession.getInstance().getAttribute("remediosOwner");
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
            if (descontos == null) {
                descontos = new ArrayList();
                descontos.add(this.desconto);
            } else {
                descontos.add(this.desconto);
            }
        }
    }

    public Integer getDesconto() {
        return null;
    }

    public List<Farmacia> getFarmacias() {
        return farmacias;
    }

    public void setFarmacias(List<Farmacia> farmacias) {
        this.farmacias = farmacias;
    }

    public void setDescontos(List<Integer> descontos) {
        this.descontos = descontos;
    }

}
