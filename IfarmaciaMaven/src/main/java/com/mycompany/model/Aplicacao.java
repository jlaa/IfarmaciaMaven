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
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author LucasPC
 */
public class Aplicacao {

    EntityManagerFactory emf = SingletonEntityManagerFactory.getInstance();
    private static Logger logger;

    public boolean CadastrarCliente(Cliente cliente) {

        EntityManager em = null;
        EntityTransaction et = null;
        logger = Logger.getGlobal();
        logger.setLevel(Level.INFO);
        try {

            em = emf.createEntityManager();
            et = em.getTransaction();
            et.begin();
            try {
                em.persist(cliente);
            } catch (ConstraintViolationException e) {

                Logger.getGlobal().info(e.getMessage());

                Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
                if (logger.isLoggable(Level.INFO)) {
                    for (ConstraintViolation violation : constraintViolations) {
                        Logger.getGlobal().log(Level.INFO, "{0}.{1}: {2}", new Object[]{violation.getRootBeanClass(), violation.getPropertyPath(), violation.getMessage()});
                    }
                }
                return false;
            }
            et.commit();
        } catch (Exception ex) {

            ex.printStackTrace();

            if (et != null) {
                et.rollback();
                return false;
            }
            return false;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return true;
    }

    public Cliente getCliente(String email, String senha) {
        EntityManager em = null;
        EntityTransaction et = null;
        Cliente cliente = null;

        try {

            em = emf.createEntityManager();
            et = em.getTransaction();
            et.begin();
            TypedQuery<Cliente> query = em.createQuery("SELECT c from Cliente c where c.email like ?1 and  c.senha like ?2", Cliente.class);
            query.setParameter(1, email);
            query.setParameter(2, senha);
            cliente = query.getSingleResult();
            et.commit();
            if (cliente != null) {
                return cliente;
            }

        } catch (Exception ex) {
            if (et != null) {
                et.rollback();
            }
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return cliente;

    }

    public boolean validarCliente(String email, String senha) {

        EntityManager em = null;
        EntityTransaction et = null;

        try {

            em = emf.createEntityManager();
            et = em.getTransaction();
            et.begin();
            TypedQuery<Cliente> query = em.createQuery("SELECT c from Cliente c where c.email like ?1 and  c.senha like ?2", Cliente.class);
            query.setParameter(1, email);
            query.setParameter(2, senha);
            Cliente cliente = query.getSingleResult();
            et.commit();
            if (cliente != null) {
                return true;
            }

        } catch (Exception ex) {
            if (et != null) {
                et.rollback();
            }
            return false;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return false;
    }

    public void inserirCartao(Cliente cliente) {
        EntityManager em = null;
        EntityTransaction et = null;

        try {
            em = emf.createEntityManager();
            et = em.getTransaction();
            et.begin();
            try {
                em.merge(cliente);
            } catch (ConstraintViolationException e) {
                Logger.getGlobal().info(e.getMessage());

                Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
                if (logger.isLoggable(Level.INFO)) {
                    for (ConstraintViolation violation : constraintViolations) {
                        Logger.getGlobal().log(Level.INFO, "{0}.{1}: {2}", new Object[]{violation.getRootBeanClass(), violation.getPropertyPath(), violation.getMessage()});
                    }
                }
            }
            et.commit();
        } catch (Exception ex) {
            if (et != null) {
                et.rollback();
            }
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void AlterarCliente(String bairro, String cidade, String estado,
            String numero, String rua, String nome, String ocupacao, String telefone,
            Cliente cliente) {

        EntityManager em = null;
        EntityTransaction et = null;
        cliente.AdicionarEndereco(rua, numero, bairro, cidade, estado);
        cliente.setNome(nome);
        cliente.setOcupacao(ocupacao);
        cliente.setTelefone(telefone);
        try {
            em = emf.createEntityManager();
            et = em.getTransaction();
            et.begin();
            try {
                em.merge(cliente);
                et.commit();
            } catch (ConstraintViolationException e) {
                Logger.getGlobal().info(e.getMessage());

                Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
                if (logger.isLoggable(Level.INFO)) {
                    for (ConstraintViolation violation : constraintViolations) {
                        Logger.getGlobal().log(Level.INFO, "{0}.{1}: {2}", new Object[]{violation.getRootBeanClass(), violation.getPropertyPath(), violation.getMessage()});
                    }
                }
            }
        } catch (Exception ex) {
            if (et != null) {
                et.rollback();
            }
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void CadastrarFarmacia(Farmacia farmacia) {

        EntityManager em = null;
        EntityTransaction et = null;
        try {

            em = emf.createEntityManager();
            et = em.getTransaction();
            et.begin();
            em.persist(farmacia);
            et.commit();
        } catch (Exception ex) {
            if (et != null) {
                et.rollback();
            }
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Farmacia> getFarmacia(String nomeFarmacia, String nomeRemedio) {
        EntityManager em = null;
        EntityTransaction et = null;
        List<Farmacia> farmacia = null;

        try {

            em = emf.createEntityManager();
            et = em.getTransaction();
            et.begin();
            TypedQuery<Farmacia> query = em.createQuery("SELECT f from Farmacia f,Remedio r where f.endereco.cidade  like ?1 and  r.nome like ?2", Farmacia.class);
            query.setParameter(1, nomeFarmacia);
            query.setParameter(2, nomeRemedio);
            farmacia = query.getResultList();
            et.commit();
        } catch (Exception ex) {
            if (et != null) {
                et.rollback();
            }
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return farmacia;
    }

    public List<Farmacia> listaFarmacia() {
        EntityManager em = null;
        EntityTransaction et = null;
        List<Farmacia> farmacia = null;

        try {

            em = emf.createEntityManager();
            et = em.getTransaction();
            et.begin();
            TypedQuery<Farmacia> query = em.createQuery("SELECT f from Farmacia f", Farmacia.class);
            farmacia = query.getResultList();
            try {
                et.commit();
            } catch (ConstraintViolationException ex) {

                Logger.getGlobal().info(ex.getMessage());

                Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
                if (logger.isLoggable(Level.INFO)) {
                    for (ConstraintViolation violation : constraintViolations) {
                        Logger.getGlobal().log(Level.INFO, "{0}.{1}: {2}", new Object[]{violation.getRootBeanClass(), violation.getPropertyPath(), violation.getMessage()});
                    }
                }

            }
        } catch (Exception ex) {
            if (et != null) {
                et.rollback();
            }
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return farmacia;
    }

    public List<Farmacia> pesquisaRemedio(String cidade, String nomeRemedio) {
        EntityManager em = null;
        EntityTransaction et = null;
        List<Farmacia> farmacias = new ArrayList();
        List<Farmacia> temp;

        try {

            em = emf.createEntityManager();
            et = em.getTransaction();
            et.begin();
            TypedQuery<Farmacia> query = em.createQuery("SELECT f from Farmacia f where f.endereco.cidade like ?1", Farmacia.class);
            query.setParameter(1, cidade);
            temp = query.getResultList();
            for (int i = 0; i < temp.size(); i++) {

                for (int j = 0; j < temp.get(i).getRemedios().size(); j++) {
                    if (temp.get(i).getRemedios().get(j).getNome().equals(nomeRemedio)) {
                        farmacias.add(temp.get(i));
                    }
                }
            }
            et.commit();
        } catch (Exception ex) {
            if (et != null) {
                et.rollback();
            }
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return farmacias;
    }

    public List<Remedio> listaRemedio() {
        EntityManager em = null;
        EntityTransaction et = null;
        List<Remedio> remedio = null;

        try {

            em = emf.createEntityManager();
            et = em.getTransaction();
            et.begin();
            TypedQuery<Remedio> query = em.createQuery("SELECT r from Remedio r", Remedio.class);
            remedio = query.getResultList();
            et.commit();
        } catch (Exception ex) {
            if (et != null) {
                et.rollback();
            }
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return remedio;
    }

    public List<String> listaFileName() {
        EntityManager em = null;
        EntityTransaction et = null;
        List<String> string = null;

        try {

            em = emf.createEntityManager();
            et = em.getTransaction();
            et.begin();
            TypedQuery<String> query = em.createQuery("SELECT f.filename from Farmacia f", String.class);
            string = query.getResultList();
            et.commit();
        } catch (Exception ex) {
            if (et != null) {
                et.rollback();
            }
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return string;
    }

    public Remedio getRemedio(Long id) {
        EntityManager em = null;
        EntityTransaction et = null;
        Remedio remedio = null;
        try {

            em = emf.createEntityManager();
            et = em.getTransaction();
            et.begin();            
            remedio = em.find(Remedio.class,id);
            et.commit();
        } catch (Exception ex) {
            if (et != null) {
                et.rollback();
            }
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return remedio;
    }

}
