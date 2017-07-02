/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author LucasPC
 */
public class singletonSession {

    //create an object of SingleObject
    private static final FacesContext facesContext = FacesContext.getCurrentInstance();
    private static final  HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);

    //make the constructor private so that this class cannot be
    //instantiated
    private singletonSession() {
    }

    //Get the only object available
    public static HttpSession getInstance() {
        return session;
    }

    
    
    
}
