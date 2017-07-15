/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author LucasPC
 */
@Entity
@Table(name = "OWNER_TABLE")
@Access(AccessType.FIELD)
public class Owner extends Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_OWNER", nullable = false)
    private long id;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "TB_FARMACIA_OWNER", joinColumns = {
        @JoinColumn(name = "ID_OWNER")},
            inverseJoinColumns = {
                @JoinColumn(name = "ID_FARMACIA")
            })
    private List<Farmacia> farmacia;

    @OneToOne(mappedBy = "onwer", optional = false)
    private Cliente cliente;

    public Owner() {

    }

    public List<Farmacia> getFarmacia() {
        return farmacia;
    }

    public void setFarmacia(List<Farmacia> farmacia) {
        this.farmacia = farmacia;
    }
    
    

}
