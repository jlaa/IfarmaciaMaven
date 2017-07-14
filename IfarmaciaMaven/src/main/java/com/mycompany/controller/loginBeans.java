/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.model.Aplicacao;
import com.mycompany.model.Cliente;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author LucasPC
 */
@ManagedBean(name = "login")
@SessionScoped
public class loginBeans implements Serializable {
    @EJB
    private Aplicacao aplicacao;
    @NotEmpty(message = "O password não pode ser vazio")
    @Length(message = "O password não pode ter mais de 20 caracteres e"
            + " menos de 6 caracteres", max = 20, min = 6)
    private String senha;
    @NotEmpty(message = "O email não pode ser vazio")
    @Email(message = "o email nao é válido")
    private String email;

    private String validacao;

    /**
     * Creates a new instance of loginBeans
     */
    public loginBeans() {

    }

    public String validarUsuario() {

        boolean logar = aplicacao.validarCliente(email, senha);
        if (logar == true) {
            Cliente cliente = aplicacao.getCliente(email, senha);
            SingletonSession.getInstance().setAttribute("clienteLogado", cliente);
            return "sucessoLogin";
        }
        return "falhaLogin";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String retornaValidacao() {
        if (email != null) {
            Cliente cliente = (Cliente) SingletonSession.getInstance().getAttribute("clienteLogado");
            if (cliente != null) {
                String valida = cliente.getLogin();

                if (valida != null) {
                    return valida;
                }
            }
        }
        return "Convidado";
    }

    public void setValidacao(String validacao) {
        this.validacao = validacao;
    }

    public String logout() {

        SingletonSession.getInstance().encerrarSessao();
        return "sair";
    }

}
