/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.model;

import static com.mycompany.model.Papel.APOSENTADO;
import static com.mycompany.model.Papel.CLIENTE;
import static com.mycompany.model.Papel.OWNER;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.ejb.EJBAccessException;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author LucasPC
 */
@Stateless
@LocalBean
@DeclareRoles({APOSENTADO, OWNER, CLIENTE})
@TransactionManagement(TransactionManagementType.CONTAINER)
public class Aplicacao {

    @Resource
    private SessionContext sessionContext;

    @PersistenceContext(name = "com.mycompany_IfarmaciaMaven_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    private static Logger logger;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean CadastrarCliente(Cliente cliente) {
        try {
            em.persist(cliente);
        } catch (Exception ex) {
            return false;
        }
        return true;

    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean atualizarGrupo(Grupo grupo) {
        try {
            em.merge(grupo);
        } catch (Exception ex) {
            return false;
        }
        return true;

    }
    

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean CadastrarFarmacia(Farmacia farmacia) {
        if (sessionContext.isCallerInRole(OWNER)) {
            try {
                em.persist(farmacia);
            } catch (Exception ex) {
                return false;
            }
            return true;
        } else {
            throw new EJBAccessException();
        }

    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean inserirCartao(Cliente cliente) {
        if (sessionContext.isCallerInRole(CLIENTE)||sessionContext.isCallerInRole(APOSENTADO)) {

            try {
                em.merge(cliente);
            } catch (Exception ex) {
                return false;
            }
            return true;
        } else {
            throw new EJBAccessException();
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void AlterarCliente(Cliente cliente) {
        if (sessionContext.isCallerInRole(CLIENTE) || sessionContext.isCallerInRole(OWNER)
                ||sessionContext.isCallerInRole(APOSENTADO)) {
            em.merge(cliente);
        } else {
            throw new EJBAccessException();
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void AlterarRemedio(Remedio remedio) {
        if (sessionContext.isCallerInRole(CLIENTE) ||sessionContext.isCallerInRole(APOSENTADO) 
                || sessionContext.isCallerInRole(OWNER)) {
            em.merge(remedio);
        } else {
            throw new EJBAccessException();
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void excluirRemedio(Remedio remedio) {
        if (sessionContext.isCallerInRole(OWNER)) {
            em.remove(em.merge(remedio));
        } else {
            throw new EJBAccessException();
        }
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Grupo getGrupo(String nome_grupo) {
        Grupo grupo;
        try {
            TypedQuery<Grupo> query = em.createQuery("SELECT g from Grupo g WHERE g.nome like ?1", Grupo.class);
            query.setParameter(1, nome_grupo);
            grupo = query.getSingleResult();
        } catch (Exception ex) {
            return null;
        }
        return grupo;

    }

    
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<String> listaNomeFarmacia() {
        if (sessionContext.isCallerInRole(CLIENTE)||sessionContext.isCallerInRole(APOSENTADO)) {

            List<String> farmacia;
            try {
                TypedQuery<String> query = em.createQuery("SELECT distinct f.endereco.cidade from Farmacia f", String.class);
                farmacia = query.getResultList();
            } catch (Exception ex) {
                return null;
            }
            return farmacia;
        } else {
            throw new EJBAccessException();
        }
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Farmacia> listaTodasFarmacias() {
        if (sessionContext.isCallerInRole(OWNER)) {

            List<Farmacia> farmacia;
            try {
                TypedQuery<Farmacia> query = em.createQuery("SELECT distinct f from Farmacia f", Farmacia.class);
                farmacia = query.getResultList();
            } catch (Exception ex) {
                return null;
            }
            return farmacia;
        } else {
            throw new EJBAccessException();
        }
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<String> listaRemedio() {
        if (sessionContext.isCallerInRole(CLIENTE)||sessionContext.isCallerInRole(APOSENTADO)) {

            List<String> remedio;
            try {
                TypedQuery<String> query = em.createQuery("SELECT distinct r.nome from Remedio r", String.class);
                remedio = query.getResultList();
            } catch (Exception ex) {
                return null;
            }
            return remedio;
        } else {
            throw new EJBAccessException();
        }
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<String> listaFileName() {
        //lembrar disso pois só quem ta podendo listarFileName é owner caso eu precisse de uma lista
        //em cliente depois 
        if (sessionContext.isCallerInRole(OWNER)) {

            List<String> string;
            try {
                TypedQuery<String> query = em.createQuery("SELECT f.filename from Farmacia f", String.class);
                string = query.getResultList();
            } catch (Exception ex) {
                return null;
            }
            return string;
        } else {
            throw new EJBAccessException();
        }
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public boolean validarCliente(String email, String senha) {
        try {
            TypedQuery<Cliente> query = em.createQuery("SELECT c from Cliente c where c.email like ?1 and  c.senha like ?2", Cliente.class);
            query.setParameter(1, email);
            query.setParameter(2, senha);
            Cliente cliente = query.getSingleResult();
            if (cliente != null) {
                return true;
            }
        } catch (Exception ex) {
            return false;
        }
        return false;

    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Cliente getCliente(String email) {
        Cliente cliente = null;
        try {
            TypedQuery<Cliente> query = em.createQuery("SELECT c from Cliente c where c.email like ?1", Cliente.class);
            query.setParameter(1, email);
            cliente = query.getSingleResult();
            if (cliente != null) {
                return cliente;
            }
        } catch (Exception ex) {
            return cliente;
        }
        return cliente;

    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Farmacia> pesquisaRemedio(String cidade, String nomeRemedio) {
        List<Farmacia> farmacia = new ArrayList();
        List<Farmacia> temp = new ArrayList();
        try {
            TypedQuery<Farmacia> query = em.createQuery("SELECT f from Farmacia f where f.endereco.cidade like ?1", Farmacia.class);
            query.setParameter(1, cidade);
            temp = query.getResultList();
            for (int i = 0; i < temp.size(); i++) {

                for (int j = 0; j < temp.get(i).getRemedios().size(); j++) {
                    if (temp.get(i).getRemedios().get(j).getNome().equals(nomeRemedio)) {
                        farmacia.add(temp.get(i));
                    }
                }
            }
        } catch (Exception ex) {
            return farmacia;
        }
        return farmacia;
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Remedio getRemedio(Long id) {
        if (sessionContext.isCallerInRole(OWNER) || sessionContext.isCallerInRole(CLIENTE) 
                ||sessionContext.isCallerInRole(APOSENTADO)) {
            return em.find(Remedio.class, id);
        } else {
            throw new EJBAccessException();
        }
    }

}
