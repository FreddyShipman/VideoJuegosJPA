package Interfaces;

import Entity.VideoJuego;
import java.util.List;

/**
 *
 * @author alfre
 */
public interface IVideoJuegoDAO {
    // CRUD básico
    boolean agregar(VideoJuego videojuego);
    boolean editar(VideoJuego videojuego);
    boolean eliminar(Long id);
    VideoJuego buscarPorId(Long id);

    // Consultas NamedQuery
    List<VideoJuego> obtenerTodos();
    List<VideoJuego> buscarPorNombre(String nombre);
    List<VideoJuego> buscarPorDesarrolladora(String desarrolladora);
    List<VideoJuego> filtrarPorPuntajeMayorA(int puntajeMinimo);
    List<VideoJuego> ordenarPorNombre();
    List<VideoJuego> ordenarPorPuntajeDesc();
    List<Object[]> contarVideojuegosPorDesarrolladora();
    List<VideoJuego> buscarSinJugadores();
    List<VideoJuego> buscarConLogrosMayorA(int puntosMinimos);
    int actualizarPuntajePorNombre(String nombre, int nuevoPuntaje);
    int eliminarPorNombre(String nombre);
}
