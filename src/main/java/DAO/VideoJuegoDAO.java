package DAO;

import Entity.VideoJuego;
import Interfaces.IVideoJuegoDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author alfre
 */

public class VideoJuegoDAO implements IVideoJuegoDAO {

    private final EntityManagerFactory emf;

    public VideoJuegoDAO() {
        this.emf = Persistence.createEntityManagerFactory("VideoJuegosPU");
    }

    @Override
    public boolean agregar(VideoJuego videojuego) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(videojuego);
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
    public boolean editar(VideoJuego videojuego) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(videojuego);
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
            VideoJuego videojuego = em.find(VideoJuego.class, id);
            if (videojuego != null) {
                em.remove(videojuego);
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
    public VideoJuego buscarPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(VideoJuego.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public List<VideoJuego> obtenerTodos() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<VideoJuego> query = em.createQuery("SELECT v FROM VideoJuego v", VideoJuego.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<VideoJuego> buscarPorNombre(String nombre) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<VideoJuego> query = em.createQuery("SELECT v FROM VideoJuego v WHERE v.nombre LIKE :nombre", VideoJuego.class);
            query.setParameter("nombre", "%" + nombre + "%");
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<VideoJuego> buscarPorDesarrolladora(String desarrolladora) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<VideoJuego> query = em.createQuery("SELECT v FROM VideoJuego v WHERE v.desarrolladora = :desarrolladora", VideoJuego.class);
            query.setParameter("desarrolladora", desarrolladora);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<VideoJuego> filtrarPorPuntajeMayorA(int puntajeMinimo) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<VideoJuego> query = em.createQuery("SELECT v FROM VideoJuego v WHERE v.puntaje > :puntajeMinimo", VideoJuego.class);
            query.setParameter("puntajeMinimo", puntajeMinimo);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<VideoJuego> ordenarPorNombre() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<VideoJuego> query = em.createQuery("SELECT v FROM VideoJuego v ORDER BY v.nombre ASC", VideoJuego.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<VideoJuego> ordenarPorPuntajeDesc() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<VideoJuego> query = em.createQuery("SELECT v FROM VideoJuego v ORDER BY v.puntaje DESC", VideoJuego.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Object[]> contarVideojuegosPorDesarrolladora() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Object[]> query = em.createQuery("SELECT v.desarrolladora, COUNT(v) FROM VideoJuego v GROUP BY v.desarrolladora", Object[].class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<VideoJuego> buscarSinJugadores() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<VideoJuego> query = em.createQuery("SELECT v FROM VideoJuego v WHERE v.jugadores IS EMPTY", VideoJuego.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<VideoJuego> buscarConLogrosMayorA(int puntosMinimos) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<VideoJuego> query = em.createQuery("SELECT DISTINCT v FROM VideoJuego v JOIN v.logros l WHERE l.puntos > :puntosMinimos", VideoJuego.class);
            query.setParameter("puntosMinimos", puntosMinimos);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            em.close();
        }
    }

    @Override
    public int actualizarPuntajePorNombre(String nombre, int nuevoPuntaje) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("UPDATE VideoJuego v SET v.puntaje = :nuevoPuntaje WHERE v.nombre = :nombre");
            query.setParameter("nuevoPuntaje", nuevoPuntaje);
            query.setParameter("nombre", nombre);
            int updatedCount = query.executeUpdate();
            em.getTransaction().commit();
            return updatedCount;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return 0;
        } finally {
            em.close();
        }
    }

    @Override
    public int eliminarPorNombre(String nombre) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("DELETE FROM VideoJuego v WHERE v.nombre = :nombre");
            query.setParameter("nombre", nombre);
            int deletedCount = query.executeUpdate();
            em.getTransaction().commit();
            return deletedCount;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return 0;
        } finally {
            em.close();
        }
    }
}