/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author LucasPC
 */
@Named(value = "carrinhoBeans")
@SessionScoped
public class carrinhoBeans implements Serializable {

    /**
     * Creates a new instance of carrinhoBeans
     */
    public carrinhoBeans() 
    {
    }
    
}
