package com.mycompany.model;

import com.mycompany.model.Cliente;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-15T03:39:43")
@StaticMetamodel(Grupo.class)
public class Grupo_ { 

    public static volatile SingularAttribute<Grupo, String> nome;
    public static volatile SingularAttribute<Grupo, Long> id;
    public static volatile ListAttribute<Grupo, Cliente> clientes;

}