package com.mycompany.model;

import com.mycompany.model.Cliente;
import com.mycompany.model.Endereco;
import com.mycompany.model.FormaDePagamento;
import com.mycompany.model.Remedio;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-29T11:17:45")
@StaticMetamodel(Farmacia.class)
public class Farmacia_ { 

    public static volatile ListAttribute<Farmacia, Cliente> clientes;
    public static volatile SingularAttribute<Farmacia, Long> id_farmacia;
    public static volatile ListAttribute<Farmacia, Remedio> remedios;
    public static volatile SingularAttribute<Farmacia, String> nome;
    public static volatile SingularAttribute<Farmacia, Endereco> endereco;
    public static volatile SingularAttribute<Farmacia, FormaDePagamento> forma_de_pagamento;

}