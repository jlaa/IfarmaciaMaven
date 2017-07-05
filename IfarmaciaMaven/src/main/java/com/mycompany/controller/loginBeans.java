/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.model.Aplicacao;
import com.mycompany.model.Cliente;
import java.io.Serializable;
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

    @NotEmpty(message = "O password não pode ser vazio")
    @Length(message = "O password não pode ter mais de 20 caracteres e"
            + " menos de 6 caracteres", max = 20, min = 6)
    private String senha;
    @NotEmpty(message = "O email não pode ser vazio")
    @Email(message = "o email nao é válido")
    private String email;

    FacesContext facesContext = FacesContext.getCurrentInstance();
    HttpSession session = singletonSession.getInstance() ;

    private String validacao;

    /**
     * Creates a new instance of loginBeans
     */
    public loginBeans() {
        
        if (session.isNew()) {
            session = (HttpSession) facesContext.getExternalContext().getSession(true);
        }

    }

    public String validarUsuario() {
        Aplicacao aplicacao = new Aplicacao();

        boolean logar = aplicacao.validarCliente(email, senha);
        if (logar == true) {
            Cliente cliente = aplicacao.getCliente(email, senha);
            session.setAttribute("cliente", cliente);
            session.setAttribute("loginName", cliente.getLogin());
            return "Index";
        }
        return "LoginError";
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
        facesContext = FacesContext.getCurrentInstance();
        session = (HttpSession) facesContext.getExternalContext().getSession(false);
        String valida = (String) session.getAttribute("loginName");
        if (valida != null) {
            return valida;
        }
        return "Convidado";
    }

    public void setValidacao(String validacao) {
        this.validacao = validacao;
    }

    public String Sair() {
        
        
        return "Sair";
    }

}
