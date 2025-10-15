package Interfaces;

import Entity.Logro;
import java.util.List;

/**
 *
 * @author alfre
 */
public interface ILogroDAO {

    // CRUD Basico
    boolean agregar(Logro logro);
    boolean editar(Logro logro);
    boolean eliminar(Long id);
    Logro buscarPorId(Long id);

    // Consultas Avanzadas
    List<Logro> obtenerTodos();
    List<Logro> buscarConPuntajeMayorAlPromedio();
}