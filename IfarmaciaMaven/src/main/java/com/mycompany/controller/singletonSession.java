/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.model.Cliente;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author LucasPC
 */
public class SingletonSession {

    private static SingletonSession instance;

    private SingletonSession() {
    }

    public static SingletonSession getInstance() {
        if (instance == null) {
            instance = new SingletonSession();        }

        return instance;
    }

    private ExternalContext currentExternalContext() {
        if (FacesContext.getCurrentInstance() == null) {
            throw new RuntimeException("O FacesContext não pode ser chamado fora de uma requisição HTTP");
        } else {
            return FacesContext.getCurrentInstance().getExternalContext();
        }
    }

    public Cliente getUsuarioLogado() {
        return (Cliente) getAttribute("clienteLogado");
    }

    public void setUsuarioLogado(Cliente usuario) {
        setAttribute("clienteLogado", usuario);
    }

    public void encerrarSessao() {
        currentExternalContext().invalidateSession();
    }

    public Object getAttribute(String cliente) {
        return currentExternalContext().getSessionMap().get(cliente);
    }

    public void setAttribute(String nome, Object valor) {
        currentExternalContext().getSessionMap().put(nome, valor);
    }

}
