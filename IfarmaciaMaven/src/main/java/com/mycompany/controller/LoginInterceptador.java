/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.model.Papel;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.print.attribute.standard.Severity;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;

/**
 *
 * @author MASC
 */
@Interceptor
public class LoginInterceptador {

    @Resource
    private SessionContext contexto;

    private HttpServletRequest getHttpServletRequest(InvocationContext ic) {
        HttpServletRequest request = null;
        for (Object parameter : ic.getParameters()) {
            if (parameter instanceof HttpServletRequest) {
                request = (HttpServletRequest) parameter;
            }
        }

        return request;
    }

    private HttpHeaders getHttpHeaders(InvocationContext ic) {
        HttpHeaders headers = null;

        for (Object parameter : ic.getParameters()) {
            if (parameter instanceof HttpHeaders) {
                headers = (HttpHeaders) parameter;
            }
        }

        return headers;
    }

    private boolean valido(String valor) {
        return valor != null && valor.trim().length() > 0;
    }

    @AroundInvoke
    public Object interceptar(InvocationContext ic) throws Exception {
        Object resultado = new Object();
        HttpServletRequest request = null;
        HttpHeaders headers;

        try {
            request = getHttpServletRequest(ic);
            headers = getHttpHeaders(ic);

            String autorizacao = headers.getHeaderString("Authorization");
            String autorizacaoArray[] = new String[]{null, null};

            if (autorizacao != null && autorizacao.length() > 6) {
                autorizacao = autorizacao.substring(6);
                byte[] bytes = Base64.getDecoder().decode(autorizacao);
                String utf8 = new String(bytes, StandardCharsets.UTF_8);
                if (utf8.contains(":")) {
                    autorizacaoArray = utf8.split(":");
                }
            }

            String email = autorizacaoArray[0];
            String senha = autorizacaoArray[1];

            if (valido(email) && valido(senha)) {
                try {
                    request.login(email, senha);
                    request.getSession(true);

                    if (contexto.isCallerInRole(Papel.CLIENTE)) {
                        resultado = ic.proceed();
                    } else {
                        FacesMessage message = new FacesMessage("acesso não autorizado", senha);
                        FacesContext.getCurrentInstance().addMessage(null, message);

                    }

                } catch (ServletException ex) {
                    FacesMessage message = new FacesMessage("login invalido", senha);
                    FacesContext.getCurrentInstance().addMessage(null, message);
                }
            } else {
                FacesMessage message = new FacesMessage("credenciais omitidas", senha);
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        } finally {
            if (request != null) {
                if (request.getSession(false) != null) {
                    request.getSession(false).invalidate();
                }

                request.logout();
            }
        }

        return resultado;
    }
}
