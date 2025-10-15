package DAO;

import Entity.Logro;
import Interfaces.ILogroDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author alfre
 */
public class LogroDAO implements ILogroDAO {

    private EntityManagerFactory emf;

    public LogroDAO() {
        this.emf = Persistence.createEntityManagerFactory("VideoJuegosPU");
    }

    @Override
    public boolean agregar(Logro logro) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(logro);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public boolean editar(Logro logro) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(logro);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public boolean eliminar(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Logro logro = em.find(Logro.class, id);
            if (logro != null) {
                em.remove(logro);
            }
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public Logro buscarPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Logro.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Logro> obtenerTodos() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Logro> query = em.createQuery("SELECT l FROM Logro l", Logro.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Logro> buscarConPuntajeMayorAlPromedio() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Logro> query = em.createQuery(
                "SELECT l FROM Logro l WHERE l.puntos > (SELECT AVG(l2.puntos) FROM Logro l2)", 
                Logro.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            em.close();
        }
    }
}