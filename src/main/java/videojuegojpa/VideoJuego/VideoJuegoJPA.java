package videojuegojpa.VideoJuego;

import DAO.DireccionDAO;
import DAO.JugadorDAO;
import DAO.LogroDAO;
import DAO.VideoJuegoDAO;
import Entity.Direccion;
import Entity.Jugador;
import Entity.Logro;
import Entity.VideoJuego;
import Interfaces.IDireccionDAO;
import Interfaces.IJugadorDAO;
import Interfaces.ILogroDAO;
import Interfaces.IVideoJuegoDAO;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author alfre
 */

public class VideoJuegoJPA {

    public static void main(String[] args) {
        IVideoJuegoDAO videoJuegoDAO = new VideoJuegoDAO();
        IJugadorDAO jugadorDAO = new JugadorDAO();
        ILogroDAO logroDAO = new LogroDAO();
        IDireccionDAO direccionDAO = new DireccionDAO();

        System.out.println("--- PRUEBA DE INSERCION Y RELACIONES ---");

        Direccion dir = new Direccion();
        dir.setCalle("Avenida Siempre Viva");
        dir.setNumero("742");
        dir.setColonia("Springfield");

        Jugador homero = new Jugador();
        homero.setPseudonimo("El Homero");
        homero.setSexo("Masculino");
        homero.setFechaNacimiento(LocalDate.of(1980, 5, 12));
        homero.setDireccion(dir);
        jugadorDAO.agregar(homero);
        System.out.println("Jugador 'Homero' guardado con su direccion.");

        VideoJuego pacman = new VideoJuego();
        pacman.setNombre("Pac-Man");
        pacman.setDesarrolladora("Namco");
        pacman.setPuntaje(90);
        
        Logro comerFantasmas = new Logro();
        comerFantasmas.setNombre("Come-Fantasmas");
        comerFantasmas.setPuntos(100);
        comerFantasmas.setVideojuego(pacman);

        Logro primerNivel = new Logro();
        primerNivel.setNombre("Supera el primer nivel");
        primerNivel.setPuntos(50);
        primerNivel.setVideojuego(pacman);

        Set<Logro> logrosPacman = new HashSet<>();
        logrosPacman.add(comerFantasmas);
        logrosPacman.add(primerNivel);
        pacman.setLogros(logrosPacman);
        
        Set<Jugador> jugadoresPacman = new HashSet<>();
        jugadoresPacman.add(homero);
        pacman.setJugadores(jugadoresPacman);

        videoJuegoDAO.agregar(pacman);
        System.out.println("Videojuego 'Pac-Man' guardado con sus logros y jugador.");

        System.out.println("\n--- PRUEBA DE CONSULTAS ---");
        System.out.println("Videojuegos de Namco: " + videoJuegoDAO.buscarPorDesarrolladora("Namco"));
        List<Object[]> conteo = videoJuegoDAO.contarVideojuegosPorDesarrolladora();
        for (Object[] obj : conteo) {
            System.out.println("Desarrolladora: " + obj[0] + ", Total: " + obj[1]);
        }
        System.out.println("Jugadores en Springfield: " + jugadorDAO.buscarPorColoniaYVideojuegos("Springfield", 0));
        
        System.out.println("Logros con puntaje mayor al promedio: " + logroDAO.buscarConPuntajeMayorAlPromedio());

        System.out.println("\n--- PRUEBA DE ELIMINACION ---");
        
        VideoJuego pacmanDB = videoJuegoDAO.buscarPorId(pacman.getId());
        if(pacmanDB != null) {
            pacmanDB.setJugadores(null);
            videoJuegoDAO.editar(pacmanDB);
        }

        videoJuegoDAO.eliminar(pacman.getId());
        System.out.println("Videojuego y sus logros eliminados.");
        
        jugadorDAO.eliminar(homero.getId());
        System.out.println("Jugador y su direccion eliminados.");

        System.out.println("\n--- PRUEBAS FINALIZADAS ---");
    }
}