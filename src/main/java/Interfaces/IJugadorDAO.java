package Interfaces;

import Entity.Jugador;
import java.util.List;

/**
 *
 * @author alfre
 */
public interface IJugadorDAO {

    // CRUD Basico
    boolean agregar(Jugador jugador);
    boolean editar(Jugador jugador);
    boolean eliminar(Long id);
    Jugador buscarPorId(Long id);

    // Consultas Avanzadas
    List<Jugador> obtenerTodos();
    List<Object[]> buscarConMasVideojuegos();
    List<Jugador> buscarPorSumaPuntosLogrosMayorA(int totalPuntos);
    List<Jugador> buscarPorColoniaYVideojuegos(String colonia, long cantidadVideojuegos);
    List<Jugador> ordenarPorEdadDesc();
    List<Object[]> buscarConDireccionOrdenadoPorColonia();

}