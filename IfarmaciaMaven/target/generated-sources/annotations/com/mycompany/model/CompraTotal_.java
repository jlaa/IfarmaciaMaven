package com.mycompany.model;

import com.mycompany.model.Cliente;
import com.mycompany.model.CompraItem;
import com.mycompany.model.Farmacia;
import com.mycompany.model.FormaDePagamento;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-29T11:17:45")
@StaticMetamodel(CompraTotal.class)
public class CompraTotal_ { 

    public static volatile SingularAttribute<CompraTotal, Long> id;
    public static volatile SingularAttribute<CompraTotal, Farmacia> vendedor;
    public static volatile SingularAttribute<CompraTotal, Cliente> cliente;
    public static volatile SingularAttribute<CompraTotal, Boolean> cancelada;
    public static volatile ListAttribute<CompraTotal, CompraItem> itens;
    public static volatile SingularAttribute<CompraTotal, Date> dataCompra;
    public static volatile SingularAttribute<CompraTotal, FormaDePagamento> formaPagamento;

}