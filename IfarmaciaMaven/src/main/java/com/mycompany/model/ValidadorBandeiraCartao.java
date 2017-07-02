/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.model;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author LucasPC
 */
public class ValidadorBandeiraCartao implements ConstraintValidator<ValidaBandeiraCartao, String> {
    private List<String> cartao;
     @Override
    public void initialize(ValidaBandeiraCartao validavandeiracartao) {
        this.cartao = new ArrayList<>();
        this.cartao.add("Visa");
        this.cartao.add("MasterCard");
        this.cartao.add("DinersClub");
        this.cartao.add("AmericanExpress");
        this.cartao.add("HiperCard");
        this.cartao.add("Aura");
        this.cartao.add("Elo");
        this.cartao.add("Amex");
    
    }

    @Override
    public boolean isValid(String valor, ConstraintValidatorContext context) {
        return valor == null ? false : cartao.contains(valor);
    }
}
