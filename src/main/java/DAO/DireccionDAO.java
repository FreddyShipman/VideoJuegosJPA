package DAO;

import Entity.Direccion;
import Interfaces.IDireccionDAO;
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

public class DireccionDAO implements IDireccionDAO {

    private EntityManagerFactory emf;

    public DireccionDAO() {
        this.emf = Persistence.createEntityManagerFactory("VideoJuegosPU");
    }

    @Override
    public boolean agregar(Direccion direccion) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(direccion);
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
    public boolean editar(Direccion direccion) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(direccion);
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
            Direccion direccion = em.find(Direccion.class, id);
            if (direccion != null) {
                em.remove(direccion);
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
    public Direccion buscarPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Direccion.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Direccion> obtenerTodas() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Direccion> query = em.createQuery("SELECT d FROM Direccion d", Direccion.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            em.close();
        }
    }
}