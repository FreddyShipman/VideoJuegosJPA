package videojuegojpa.VideoJuego;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 *
 * @author alfre
 */
public class VideoJuegoJPA {
    
    
    public static void main(String[] args) {
        System.out.println("Todo fue un exito.");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("VideoJuegosPU");
        EntityManager em = emf.createEntityManager();
    }
}
