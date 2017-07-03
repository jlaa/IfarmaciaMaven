package com.mycompany.model;

import com.mycompany.model.Cliente;
import com.mycompany.model.Endereco;
import com.mycompany.model.FormaDePagamento;
import com.mycompany.model.Remedio;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-02T21:19:59")
@StaticMetamodel(Farmacia.class)
public class Farmacia_ { 

    public static volatile SingularAttribute<Farmacia, FormaDePagamento> forma_de_pagamento;
    public static volatile SingularAttribute<Farmacia, Endereco> endereco;
    public static volatile SingularAttribute<Farmacia, Long> id_farmacia;
    public static volatile SingularAttribute<Farmacia, String> nome;
    public static volatile ListAttribute<Farmacia, Cliente> clientes;
    public static volatile ListAttribute<Farmacia, Remedio> remedios;

}