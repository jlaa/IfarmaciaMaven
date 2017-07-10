/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@TransactionManagement(TransactionManagementType.CONTAINER)
public class Aplicacao {

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
    public boolean CadastrarFarmacia(Farmacia farmacia) {
        try {
            em.persist(farmacia);
        } catch (Exception ex) {
            return false;
        }
        return true;

    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean inserirCartao(Cliente cliente) {
        try {
            em.merge(cliente);
        } catch (Exception ex) {
            return false;
        }
        return true;

    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void AlterarCliente(Cliente cliente) {
        em.merge(cliente);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void remover(Cliente cliente) {
        em.remove(cliente);
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Farmacia> listaFarmacia() {
        List<Farmacia> farmacia;
        try {
            TypedQuery<Farmacia> query = em.createQuery("SELECT f from Farmacia f", Farmacia.class);
            farmacia = query.getResultList();
        } catch (Exception ex) {
            return null;
        }
        return farmacia;
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Remedio> listaRemedio() {
        List<Remedio> remedio;
        try {
            TypedQuery<Remedio> query = em.createQuery("SELECT r from Remedio r", Remedio.class);
            remedio = query.getResultList();
        } catch (Exception ex) {
            return null;
        }
        return remedio;
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<String> listaFileName() {
        List<String> string;
        try {
            TypedQuery<String> query = em.createQuery("SELECT f.filename from Farmacia f", String.class);
            string = query.getResultList();
        } catch (Exception ex) {
            return null;
        }
        return string;
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
    public Cliente getCliente(String email, String senha) {
        Cliente cliente = null;
        try {
            TypedQuery<Cliente> query = em.createQuery("SELECT c from Cliente c where c.email like ?1 and  c.senha like ?2", Cliente.class);
            query.setParameter(1, email);
            query.setParameter(2, senha);
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
    public List<Farmacia> getFarmacia(String nomeFarmacia, String nomeRemedio) {
        List<Farmacia> farmacia = null;
        try {
            TypedQuery<Farmacia> query = em.createQuery("SELECT f from Farmacia f,Remedio r where f.endereco.cidade  like ?1 and  r.nome like ?2", Farmacia.class);
            query.setParameter(1, nomeFarmacia);
            query.setParameter(2, nomeRemedio);
            farmacia = query.getResultList();
        } catch (Exception ex) {
            return farmacia;
        }
        return farmacia;
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
        return em.find(Remedio.class, id);
    }

//    public boolean CadastrarCliente(Cliente cliente) {
//
//        EntityManager em = null;
//        EntityTransaction et = null;
//        logger = Logger.getGlobal();
//        logger.setLevel(Level.INFO);
//        try {
//
//            em = emf.createEntityManager();
//            et = em.getTransaction();
//            et.begin();
//            try {
//                em.persist(cliente);
//            } catch (ConstraintViolationException e) {
//
//                Logger.getGlobal().info(e.getMessage());
//
//                Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
//                if (logger.isLoggable(Level.INFO)) {
//                    for (ConstraintViolation violation : constraintViolations) {
//                        Logger.getGlobal().log(Level.INFO, "{0}.{1}: {2}", new Object[]{violation.getRootBeanClass(), violation.getPropertyPath(), violation.getMessage()});
//                    }
//                }
//                return false;
//            }
//            et.commit();
//        } catch (Exception ex) {
//
//            ex.printStackTrace();
//
//            if (et != null) {
//                et.rollback();
//                return false;
//            }
//            return false;
//        } finally {
//            if (em != null) {
//                em.close();
//            }
//        }
//        return true;
//    }
//    public Cliente getCliente(String email, String senha) {
//        EntityManager em = null;
//        EntityTransaction et = null;
//        Cliente cliente = null;
//
//        try {
//
//            em = emf.createEntityManager();
//            et = em.getTransaction();
//            et.begin();
//            TypedQuery<Cliente> query = em.createQuery("SELECT c from Cliente c where c.email like ?1 and  c.senha like ?2", Cliente.class);
//            query.setParameter(1, email);
//            query.setParameter(2, senha);
//            cliente = query.getSingleResult();
//            et.commit();
//            if (cliente != null) {
//                return cliente;
//            }
//
//        } catch (Exception ex) {
//            if (et != null) {
//                et.rollback();
//            }
//        } finally {
//            if (em != null) {
//                em.close();
//            }
//        }
//        return cliente;
//
//    }
//    public void inserirCartao(Cliente cliente) {
//        EntityManager em = null;
//        EntityTransaction et = null;
//
//        try {
//            em = emf.createEntityManager();
//            et = em.getTransaction();
//            et.begin();
//            try {
//                em.merge(cliente);
//            } catch (ConstraintViolationException e) {
//                Logger.getGlobal().info(e.getMessage());
//
//                Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
//                if (logger.isLoggable(Level.INFO)) {
//                    for (ConstraintViolation violation : constraintViolations) {
//                        Logger.getGlobal().log(Level.INFO, "{0}.{1}: {2}", new Object[]{violation.getRootBeanClass(), violation.getPropertyPath(), violation.getMessage()});
//                    }
//                }
//            }
//            et.commit();
//        } catch (Exception ex) {
//            if (et != null) {
//                et.rollback();
//            }
//        } finally {
//            if (em != null) {
//                em.close();
//            }
//        }
//    }
//    public void AlterarCliente(String bairro, String cidade, String estado,
//            String numero, String rua, String nome, String ocupacao, String telefone,
//            Cliente cliente) {
//
//        EntityManager em = null;
//        EntityTransaction et = null;
//        cliente.AdicionarEndereco(rua, numero, bairro, cidade, estado);
//        cliente.setNome(nome);
//        cliente.setOcupacao(ocupacao);
//        cliente.setTelefone(telefone);
//        try {
//            em = emf.createEntityManager();
//            et = em.getTransaction();
//            et.begin();
//            try {
//                em.merge(cliente);
//                et.commit();
//            } catch (ConstraintViolationException e) {
//                Logger.getGlobal().info(e.getMessage());
//
//                Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
//                if (logger.isLoggable(Level.INFO)) {
//                    for (ConstraintViolation violation : constraintViolations) {
//                        Logger.getGlobal().log(Level.INFO, "{0}.{1}: {2}", new Object[]{violation.getRootBeanClass(), violation.getPropertyPath(), violation.getMessage()});
//                    }
//                }
//            }
//        } catch (Exception ex) {
//            if (et != null) {
//                et.rollback();
//            }
//        } finally {
//            if (em != null) {
//                em.close();
//            }
//        }
//    }
//    public void CadastrarFarmacia(Farmacia farmacia) {
//
//        EntityManager em = null;
//        EntityTransaction et = null;
//        try {
//
//            em = emf.createEntityManager();
//            et = em.getTransaction();
//            et.begin();
//            em.persist(farmacia);
//            et.commit();
//        } catch (Exception ex) {
//            if (et != null) {
//                et.rollback();
//            }
//        } finally {
//            if (em != null) {
//                em.close();
//            }
//        }
//    }
//    public List<Farmacia> getFarmacia(String nomeFarmacia, String nomeRemedio) {
//        EntityManager em = null;
//        EntityTransaction et = null;
//        List<Farmacia> farmacia = null;
//
//        try {
//
//            em = emf.createEntityManager();
//            et = em.getTransaction();
//            et.begin();
//            TypedQuery<Farmacia> query = em.createQuery("SELECT f from Farmacia f,Remedio r where f.endereco.cidade  like ?1 and  r.nome like ?2", Farmacia.class);
//            query.setParameter(1, nomeFarmacia);
//            query.setParameter(2, nomeRemedio);
//            farmacia = query.getResultList();
//            et.commit();
//        } catch (Exception ex) {
//            if (et != null) {
//                et.rollback();
//            }
//        } finally {
//            if (em != null) {
//                em.close();
//            }
//        }
//        return farmacia;
//    }
//    public List<Farmacia> listaFarmacia() {
//        EntityManager em = null;
//        EntityTransaction et = null;
//        List<Farmacia> farmacia = null;
//
//        try {
//
//            em = emf.createEntityManager();
//            et = em.getTransaction();
//            et.begin();
//            TypedQuery<Farmacia> query = em.createQuery("SELECT f from Farmacia f", Farmacia.class);
//            farmacia = query.getResultList();
//            try {
//                et.commit();
//            } catch (ConstraintViolationException ex) {
//
//                Logger.getGlobal().info(ex.getMessage());
//
//                Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
//                if (logger.isLoggable(Level.INFO)) {
//                    for (ConstraintViolation violation : constraintViolations) {
//                        Logger.getGlobal().log(Level.INFO, "{0}.{1}: {2}", new Object[]{violation.getRootBeanClass(), violation.getPropertyPath(), violation.getMessage()});
//                    }
//                }
//
//            }
//        } catch (Exception ex) {
//            if (et != null) {
//                et.rollback();
//            }
//        } finally {
//            if (em != null) {
//                em.close();
//            }
//        }
//        return farmacia;
//    }
//    public List<Farmacia> pesquisaRemedio(String cidade, String nomeRemedio) {
//        EntityManager em = null;
//        EntityTransaction et = null;
//        List<Farmacia> farmacias = new ArrayList();
//        List<Farmacia> temp;
//
//        try {
//
//            em = emf.createEntityManager();
//            et = em.getTransaction();
//            et.begin();
//            TypedQuery<Farmacia> query = em.createQuery("SELECT f from Farmacia f where f.endereco.cidade like ?1", Farmacia.class);
//            query.setParameter(1, cidade);
//            temp = query.getResultList();
//            for (int i = 0; i < temp.size(); i++) {
//
//                for (int j = 0; j < temp.get(i).getRemedios().size(); j++) {
//                    if (temp.get(i).getRemedios().get(j).getNome().equals(nomeRemedio)) {
//                        farmacias.add(temp.get(i));
//                    }
//                }
//            }
//            et.commit();
//        } catch (Exception ex) {
//            if (et != null) {
//                et.rollback();
//            }
//        } finally {
//            if (em != null) {
//                em.close();
//            }
//        }
//        return farmacias;
//    }
//    public List<Remedio> listaRemedio() {
//        EntityManager em = null;
//        EntityTransaction et = null;
//        List<Remedio> remedio = null;
//
//        try {
//
//            em = emf.createEntityManager();
//            et = em.getTransaction();
//            et.begin();
//            TypedQuery<Remedio> query = em.createQuery("SELECT r from Remedio r", Remedio.class);
//            remedio = query.getResultList();
//            et.commit();
//        } catch (Exception ex) {
//            if (et != null) {
//                et.rollback();
//            }
//        } finally {
//            if (em != null) {
//                em.close();
//            }
//        }
//        return remedio;
//    }
//    public List<String> listaFileName() {
//        EntityManager em = null;
//        EntityTransaction et = null;
//        List<String> string = null;
//
//        try {
//
//            em = emf.createEntityManager();
//            et = em.getTransaction();
//            et.begin();
//            TypedQuery<String> query = em.createQuery("SELECT f.filename from Farmacia f", String.class);
//            string = query.getResultList();
//            et.commit();
//        } catch (Exception ex) {
//            if (et != null) {
//                et.rollback();
//            }
//        } finally {
//            if (em != null) {
//                em.close();
//            }
//        }
//        return string;
//    }
//    public Remedio getRemedio(Long id) {
//        EntityManager em = null;
//        EntityTransaction et = null;
//        Remedio remedio = null;
//        try {
//
//            em = emf.createEntityManager();
//            et = em.getTransaction();
//            et.begin();
//            remedio = em.find(Remedio.class, id);
//            et.commit();
//        } catch (Exception ex) {
//            if (et != null) {
//                et.rollback();
//            }
//        } finally {
//            if (em != null) {
//                em.close();
//            }
//        }
//        return remedio;
//    }
}
