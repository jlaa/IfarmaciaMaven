/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;



/**
 *
 * @author LucasPC
 */
@Embeddable
public class Endereco implements Serializable
{
    @NotBlank
    @Size(max = 150)
    @Column(name="END_TXT_RUA",nullable=false)
    private String rua;
    
    @NotNull
    @Min(1)
    @Max(9999)
    @Column(name="END_TXT_NUMERO",nullable=false)
    private String numero;
    
    @NotBlank
    @Size(max = 150)
    @Column(name="END_TXT_BAIRRO",nullable=false)
    private String bairro;
    
    @NotBlank
    @Size(max = 50)
    @Column(name="END_TXT_CIDADE",nullable=false)
    private String cidade;
    
    @NotBlank
    @ValidaEstados
    @Size(min = 2, max = 2)
    @Column(name="END_TXT_ESTADO",nullable=false)
    private String estado;
    

    public void CadastrarEndereco(String rua,String numero,String bairro,String cidade,String estado)
    {
       
        this.cidade=cidade;
        this.bairro=bairro;
        this.numero=numero;
        this.rua=rua;
        this.estado=estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

  
    
   
    public String getRua() {
        return rua;
    }
    
    public String getNumero() {
        return numero;
    }
    
    public String getBairro() {
        return bairro;
    }    
    
  
   
    public void setRua(String rua) {
        this.rua = rua;
    }
    
    public void setNumero(String numero) {
        this.numero = numero;
    }
    
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    
    
      public void setCidade(String cidade) {
        this.cidade = cidade;
    }
}
