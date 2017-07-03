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
public class ValidadorFormaPagamento implements ConstraintValidator<ValidaFormaPagamento, String> {
    private List<String> formapagamento;
     @Override
    public void initialize(ValidaFormaPagamento validaformapagamento) {
        this.formapagamento = new ArrayList<>();
        this.formapagamento.add("presencialmente");
        this.formapagamento.add("cartao");    
    }

    @Override
    public boolean isValid(String valor, ConstraintValidatorContext context) {
        return valor == null ? false : formapagamento.contains(valor);
    }
}
