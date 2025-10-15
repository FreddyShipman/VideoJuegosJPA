package DAO;

import Entity.Jugador;
import Interfaces.IJugadorDAO;
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

public class JugadorDAO implements IJugadorDAO {

    private final EntityManagerFactory emf;

    public JugadorDAO() {
        this.emf = Persistence.createEntityManagerFactory("VideoJuegosPU");
    }

    @Override
    public boolean agregar(Jugador jugador) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(jugador);
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
    public boolean editar(Jugador jugador) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(jugador);
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
            Jugador jugador = em.find(Jugador.class, id);
            if (jugador != null) {
                em.remove(jugador);
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
    public Jugador buscarPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Jugador.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Jugador> obtenerTodos() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Jugador> query = em.createQuery("SELECT j FROM Jugador j", Jugador.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Object[]> buscarConMasVideojuegos() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Object[]> query = em.createQuery(
                "SELECT j.pseudonimo, COUNT(v) FROM Jugador j JOIN j.videojuegos v GROUP BY j.pseudonimo ORDER BY COUNT(v) DESC", 
                Object[].class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Jugador> buscarPorSumaPuntosLogrosMayorA(int totalPuntos) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Jugador> query = em.createQuery(
                "SELECT j FROM Jugador j JOIN j.videojuegos v JOIN v.logros l GROUP BY j HAVING SUM(l.puntos) > :totalPuntos", 
                Jugador.class);
            query.setParameter("totalPuntos", totalPuntos);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Jugador> buscarPorColoniaYVideojuegos(String colonia, long cantidadVideojuegos) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Jugador> query = em.createQuery(
                "SELECT j FROM Jugador j WHERE j.direccion.colonia = :colonia AND SIZE(j.videojuegos) > :cantidad", 
                Jugador.class);
            query.setParameter("colonia", colonia);
            query.setParameter("cantidad", (int) cantidadVideojuegos);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Jugador> ordenarPorEdadDesc() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Jugador> query = em.createQuery("SELECT j FROM Jugador j ORDER BY j.fechaNacimiento ASC", Jugador.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Object[]> buscarConDireccionOrdenadoPorColonia() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Object[]> query = em.createQuery(
                "SELECT j.pseudonimo, j.direccion.calle, j.direccion.colonia FROM Jugador j ORDER BY j.direccion.colonia", 
                Object[].class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            em.close();
        }
    }
}